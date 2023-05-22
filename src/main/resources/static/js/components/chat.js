let usernamePage = document.querySelector('#username-page');
let chatPage = document.querySelector('#chat-page');
let usernameForm = document.querySelector('#usernameForm');
let messageForm = document.querySelector('#messageForm');//ok
let $exitForm = $("#roomExitForm");
let messageInput = document.querySelector('#message');//ok
let messageArea = $(".chat-message-container");//ok
let connectingElement = $(".chat-connection-container");//ok

let stompClient = null;
let username = null;

let colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

// roomId 파라미터 가져오기
let roomId;

let chatRoomList;

$.ajax({
    type: "GET",
    url: "/chat/list",
    data: {},
    dataType: "json",
    success: function (chatRooms) {
        chatRoomList = chatRooms;
        console.log(chatRooms)
        if (chatRooms.length == 0) {
            return;
        }
        chatRooms.forEach(room => showChatRoom(room));
    }
});

$("button[name='createRoom']").on("click", function () {

    let roomName = $("input[name='roomName']").val();

    if (roomName === "" || roomName == null) {
        return;
    }

    $.ajax({
        type: "POST",
        url: "/chat/createroom",
        data: JSON.stringify({roomName: roomName}),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (chatRoom) {
            showChatRoom(chatRoom);
        }
    });
});

// 채팅내역 조회
$("button[name='historyRoomId']").on("click", function () {

    let roomId = $("input[name='historyRoomId']").val();

    if (roomId === "" || roomId == null) {
        return;
    }

    $.ajax({
        type: "GET",
        url: "/chat/history",
        data: {roomId: roomId},
        dataType: "json",
        success: function (chatList) {
            console.log(chatList);
            chatList.forEach(chat => {
                let text = `
                          <p>${chat}</p>
                    `
                $(".chat-history").append(text);
            })
        }
    });
});

function showChatRoom(chatRoom) {
    let text = `
                <li class="chat_li flex">
                    <div class="li_profile_img">
                        <img src="" alt="" width="45px" height="45px">
                    </div>
                    <div class="li_chat_name_and_history flex">
                        <div class="li_profile_nickname">RoomName : ${chatRoom.roomName}</div>
                        <div class="li_chat_history">
                            도담도담에 오신 것을 환영합니다!
                        </div>
                    </div>
                    <div class="li_chat_history_and_date flex">
                        <div class="li_date">
                            <span class="current_msg_date">RoomId : ${chatRoom.roomId}</span>
                        </div>
                        <div class="li_unchecked_msg_container">
                            <div class="li_unchecked_msg"></div>
                        </div>
                    </div>
                </li>
        `
    $(".chat-list").append(text);
}

function connect(event) {
    event.preventDefault();
    username = document.querySelector('#name').value.trim();

    // username 중복 확인
    // isDuplicateName();

    // usernamePage 에 hidden 속성 추가해서 가리고 chatPage를 등장시킴
    // usernamePage.classList.add('hidden');
    // chatPage.classList.remove('hidden');

    // 연결하고자하는 Socket 의 endPoint
    let socket = new SockJS('/ws-stomp');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
}

function onConnected() {

    roomId = $("#roomId").val();

    // sub 할 url => /sub/chat/room/roomId 로 구독한다
    stompClient.subscribe('/sub/chat/room/' + roomId, onMessageReceived);

    // 서버에 username 을 가진 유저가 들어왔다는 것을 알림
    // /pub/chat/enterUser 로 메시지를 보냄
    stompClient.send("/pub/chat/enterUser",
        {},
        JSON.stringify({
            "roomId": roomId,
            sender: username,
            type: 'ENTER'
        })
    )
    console.log("룸ID : " + roomId);
    console.log("이름 : " + username);
    // connectingElement.classList.add('hidden');

}

// 유저 닉네임 중복 확인
function isDuplicateName() {

    $.ajax({
        type: "GET",
        url: "/chat/duplicateName",
        data: {
            "username": username,
            "roomId": roomId
        },
        success: function (data) {
            console.log("함수 동작 확인 : " + data);

            username = data;
        }
    })

}

// 유저 리스트 받기
// ajax 로 유저 리스를 받으며 클라이언트가 입장/퇴장 했다는 문구가 나왔을 때마다 실행된다.
function getUserList() {
    let text = "";

    $.ajax({
        type: "GET",
        url: "/chat/userList",
        data: {
            "roomId": roomId
        },
        success: function (data) {
            data.forEach(user =>
                text += `
                    <p>${user}</p>
                `
            );
            messageArea.append(text);
        }
    })
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

// 메시지 전송때는 JSON 형식을 메시지를 전달한다.
function sendMessage(event) {
    let messageContent = messageInput.value.trim();

    if (messageContent && stompClient) {
        let chatMessage = {
            "roomId": roomId,
            sender: username,
            message: messageInput.value,
            type: 'TALK'
        };

        stompClient.send("/pub/chat/sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

// 메시지를 받을 때도 마찬가지로 JSON 타입으로 받으며,
// 넘어온 JSON 형식의 메시지를 parse 해서 사용한다.
function onMessageReceived(payload) {
    //console.log("payload 들어오냐? :"+payload);
    let chat = JSON.parse(payload.body);

    let text = "";

    if (chat.type === 'ENTER') {  // chatType 이 enter 라면 아래 내용
        text += `<p>${chat.message}</p>`
        getUserList();

    } else if (chat.type === 'LEAVE') { // chatType 가 leave 라면 아래 내용
        text += `<p>${chat.message}</p>`
        getUserList();

    } else { // chatType 이 talk 라면 아래 내용
        text += `
                <div class="sender-message">
                    <div class="sender-name">${chat.sender}</div>
                    <div class="sender-message-box">
                        <div class="sender-content">${chat.message}</div>
                        <div class="chat-date">${chat.time}</div>
                    </div>
                </div>
        `
    }

    messageArea.append(text);

    // // 만약 s3DataUrl 의 값이 null 이 아니라면 => chat 내용이 파일 업로드와 관련된 내용이라면
    // // img 를 채팅에 보여주는 작업
    // if (chat.s3DataUrl != null) {
    //     let imgElement = document.createElement('img');
    //     imgElement.setAttribute("src", chat.s3DataUrl);
    //     imgElement.setAttribute("width", "300");
    //     imgElement.setAttribute("height", "300");
    //
    //     let downBtnElement = document.createElement('button');
    //     downBtnElement.setAttribute("class", "btn fa fa-download");
    //     downBtnElement.setAttribute("id", "downBtn");
    //     downBtnElement.setAttribute("name", chat.fileName);
    //     downBtnElement.setAttribute("onclick", `downloadFile('${chat.fileName}', '${chat.fileDir}')`);
    //
    //
    //     contentElement.appendChild(imgElement);
    //     contentElement.appendChild(downBtnElement);
    //
    // } else {
    //     // 만약 s3DataUrl 의 값이 null 이라면
    //     // 이전에 넘어온 채팅 내용 보여주기
    //     let messageText = document.createTextNode(chat.message);
    //     contentElement.appendChild(messageText);
    // }

}

function exitRoom(e) {
    e.preventDefault();
    // 구독 해제(채팅방 나가기)
    stompClient.unsubscribe(roomId);
    // roomId 초기화
    roomId = null;
}

function getAvatarColor(messageSender) {
    let hash = 0;
    for (let i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    let index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', connect, true);
messageForm.addEventListener('submit', sendMessage, true);
$exitForm.on('submit', exitRoom);
