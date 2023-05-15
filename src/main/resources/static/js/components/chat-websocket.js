const $createChat = $(".create_chat");
const $sendBtn = $(".msg_submit_button");
const $inputMsg = $(".input_msg");

// 웹소켓 생성
const socket = new WebSocket("ws://localhost:10000/ws/chat");
// let webSocket;
// function connect(){
//     webSocket = new WebSocket("ws://localhost:10000/project");
//     webSocket.onopen = onOpen;
//     webSocket.onclose = onClose;
//     webSocket.onmessage = onMessage;
// }



socket.onmessage = async function (e) {
    console.log("=====================들어옴==================")
    let result;
    try {
        if (e !== null && e !== undefined) {
            //수신한 메시지의 데이터 부분을 JSON으로 파싱하여 result 변수에 저장합니다.
            result = await JSON.parse(e.data);
            console.log(result);
        }
    } catch (err) {
        console.log(err);
    }
};

$createChat.on("click", function () {
    $.ajax({
        type: "POST",
        url: "/chat/create",
        data: {},
        dataType: "json",
        success: function (result) {
            let data = {
                type: "ENTER",
                roomId: result.roomId,
                sender: "사용자1",
            }
            // WebSocket을 통해 서버로 데이터 전송
            socket.send(JSON.stringify(data));
        }
    });
});

$sendBtn.on("click", function () {
    // 메시지 입력란에서 입력된 내용 가져오기
    let message = $inputMsg.val();

    // 입력된 메시지가 비어있지 않은 경우에만 처리
    if (message !== "") {
        // 서버로 전송할 메시지 데이터 구성
        $.ajax({
            type: "POST",
            url: "/chat/create",
            data: {},
            dataType: "json",
            success: function (result) {
                let data = {
                    type: "TALK",  // 메시지 타입: "TALK"
                    roomId: result.roomId,  // 채팅방 ID
                    sender: "사용자1",  // 발신자
                    message: message  // 메시지 내용
                }

                // 메시지 입력란 비우기
                $inputMsg.val("");

                // WebSocket을 통해 서버로 데이터 전송
                socket.send(JSON.stringify(data));
            }
        });
    }
});

// // websocket 생성
// const websocket = new WebSocket("ws://localhost:10000/ws/chat");
// websocket.onmessage = onMessage;	// 소켓이 메세지를 받을 때
// websocket.onopen = onOpen;		// 소켓이 생성될때(클라이언트 접속)
// websocket.onclose = onClose;	// 소켓이 닫힐때(클라이언트 접속해제)
//
// //on exit chat room
// function onClose(evt) {
//     console.log("close event : " + evt);
// }
//
// //on entering chat room
// function onOpen(evt) {
//     console.log("open event : " + evt);
// }
//
// // on message controller
// function onMessage(msg) {
//     var data = JSON.parse(msg.data); // msg를 받으면 data 필드 안에 Json String으로 정보가 있음
//     // 필요한 정보를 Json data에서 추출
//     var senderId = data.senderId;
//     var message = data.message;
//     var time = data.time;
//     var newOne = data.newOne;
//     var outOne = data.outOne;
// }
//
// // send a message
// function send(){
//     var message = document.getElementById("msg").value;
//
//     // don't send when content is empty
//     // 채팅 입력 칸이 비어있지 않을 경우만 정보를 Json형태로 전송
//     if(message != "") {
//         let msg = {
//             'receiverId' : receiverId,
//             'message' : message
//         }
//
//         if(message != null) {
//             websocket.send(JSON.stringify(msg));	// websocket handler로 전송(서버로 전송)
//         }
//         document.getElementById("msg").value = '';
//     }
// }

// var myId = document.getElementById('myId').textContent;
// var receiverId;
// var preOnlineList;
// var masterStatusContent;
// var preMasterStatus;
// var masterStatus;
//
// if(myId != 'master') {
//     receiverId = 'master';
// } else {
//     document.getElementById('myImg').src = "starboot/assets/img/user-astronaut-solid.svg";
// }
//
// document.querySelector("#disconn").addEventListener("click", (e) => {
//     // location.href = "/";
//     window.close();
// })
//
//
//
// /********************************************************************************/
// const websocket = new WebSocket("ws://localhost:10000/ws/chat");
// websocket.onmessage = onMessage;
// websocket.onopen = onOpen;
// websocket.onclose = onClose;
//
// // send a message
// function send(){
//     var message = document.getElementById("send-message").value;
//     // don't send when content is empty
//     if(message != "") {
//         let msg = {
//             'receiverId' : receiverId,
//             'message' : message
//         }
//
//         if(message != null) {
//             websocket.send(JSON.stringify(msg));
//         }
//         document.getElementById("send-message").value = '';
//     }
// }
//
// document.querySelector("#send-button").addEventListener("click", (e) => {
//     send();
// });
//
//
// //on exit chat room
// function onClose(evt) {
//     console.log("close event : " + evt);
// }
//
// //on entering chat room
// function onOpen(evt) {
//     console.log("open event : " + evt);
// }
//
// // on message controller
// function onMessage(msg) {
//     masterStatusContent = document.getElementById('master-status-content');
//     preMasterStatus = masterStatusContent.innerHTML;
//
//     console.log('msg.data >>> ', msg.data);
//     var data = JSON.parse(msg.data);
//
//     var onlineList = data.onlineList;
//     console.log('onlineList >>>> ', onlineList);
//
//     var senderId = data.senderId;
//     console.log('senderId >>> ', senderId)
//     // var receiverId = data.receiverId;
//     // console.log('receiverId >>> ', receiverId)
//     var message = data.message;
//     console.log('message >>> ', message)
//     var time = data.time;
//     console.log('time >>> ', time)
//     masterStatus = data.masterStatus;
//     console.log('preMasterStatus >>> ', preMasterStatus)
//     console.log('masterStatus >>> ', masterStatus)
//     var newOne = data.newOne;
//     console.log('newOne >>> ', newOne);
//     var outOne = data.outOne;
//     console.log('outOne >>> ', outOne);
//
//
//
//     // when user login
//     // first login master -> get all onlined user list
//     if(newOne != null) {
//         console.log("new One is not null");
//         if(myId == 'master' && newOne == "master") {
//             getOnlineList(onlineList);
//         } else if(myId == "master" && newOne != "master") {
//             console.log("new one login >>>> " , newOne);
//             insertOnlineList(newOne);
//         }
//     }
//     // when user disconnect
//     if(outOne != null && myId == 'master') {
//         console.log("user disconnect >>> ", outOne);
//         deleteOnlieList(outOne);
//     }
//
//     // save or show message
//     if(myId=='master' && senderId != 'master' && receiverId != senderId) {
//         addStagingMessage(senderId, time, message);
//     } else {
//         insertMessage(senderId, time, message);
//     }
//
//
//     // update master status
//     updateMasterStatus();
//     // scroll down
//     scrollDown();
// }
//
// // insert a message
// function insertMessage(senderId, time, message) {
//     var chatContent =  document.querySelector("#chat-content");
//     if(senderId == myId) {
//         // insert a message into chat content to myself
//         var li = document.createElement('li');
//         li.classList.add('message-li', 'clearfix', 'float-right');
//         var infoDiv = document.createElement('div');
//         infoDiv.classList.add('message-data');
//         li.appendChild(infoDiv);
//         var timeSpan = document.createElement('span');
//         timeSpan.classList.add('message-data-time');
//         timeSpan.textContent = time;
//         infoDiv.appendChild(timeSpan);
//         var msgDiv = document.createElement('div');
//         msgDiv.classList.add('message', 'my-message');
//         msgDiv.textContent = message;
//         li.appendChild(msgDiv);
//
//         chatContent.appendChild(li);
//     } else {
//         // insert a message into chat content to other
//         var li = document.createElement('li');
//         li.classList.add('message-li', 'clearfix');
//         var infoDiv = document.createElement('div');
//         infoDiv.classList.add('message-data');
//         li.appendChild(infoDiv);
//         var timeSpan = document.createElement('span');
//         timeSpan.classList.add('message-data-time');
//         timeSpan.textContent = time;
//         infoDiv.appendChild(timeSpan);
//         var msgDiv = document.createElement('div');
//         msgDiv.classList.add('message', 'other-message');
//         msgDiv.textContent = message;
//         li.appendChild(msgDiv);
//
//         chatContent.appendChild(li);
//     }
// }
//
// // save staging messages
// function addStagingMessage(senderId, time, message) {
//
//     var container = [];
//     var data = {
//         "time":time,
//         "message":message,
//         "senderId":senderId
//     }
//     console.log('staging message data >>> ', data)
//     if(sessionStorage.getItem(senderId) != null) {
//         container = JSON.parse(sessionStorage.getItem(senderId));
//         console.log('stagine message container >>> ', container);
//         container.push(data);
//     } else {
//         container.push(data);
//     }
//     sessionStorage.setItem(senderId, JSON.stringify(container));
//
//     if(document.getElementById(senderId) != null) {
//         var circle = document.getElementById(senderId).querySelector('.circle');
//         var count = document.getElementById(senderId).querySelector('.circle > .staging-count');
//         var n = count.textContent;
//         if(n == ""){
//             n = 0
//         }
//         n ++;
//         circle.classList.remove('d-none');
//         count.textContent = n;
//     }
// }
//
// // onclick onlined user icon
// function activeToggle(element){
//     if(!element.classList.contains('active')){
//         element.classList.add('active');
//     } else {
//         element.classList.remove('active');
//     };
//     var preReceiverId = receiverId;
//     receiverId = element.querySelector('.about > .name').textContent;
//     console.log('<<<< toggleAction >>>>>')
//     console.log('receiverId >>> ', receiverId);
//     console.log('preReceiverId >>> ', preReceiverId);
//
//     if(receiverId != preReceiverId && preReceiverId != null)
//         document.getElementById(preReceiverId).classList.remove('active');
//
//     setChatHistory(preReceiverId);
//     document.getElementById('chat-content').innerHTML = "";
//     getChatHistory(receiverId);
//     divideChatSection(receiverId);
//     if(document.getElementById(receiverId).querySelector('.circle') != null) {
//         document.getElementById(receiverId).querySelector('.circle > .staging-count').textContent = "";
//         document.getElementById(receiverId).querySelector('.circle').classList.add('d-none');
//     }
// }
//
// // insert all users into online list
// function getOnlineList(onlineList){
//     var onlineUser =  document.querySelector("#online-user");
//     onlineUser.innerHTML = "";
//     onlineList.forEach(user => {
//         insertOnlineList(user);
//     });
// }
//
// // insert online user list
// function insertOnlineList(user) {
//
//     if(document.getElementById(user) == null) {
//         var onlineUser =  document.querySelector("#online-user");
//
//         var li = document.createElement('li');
//         li.classList.add('clearfix');
//         li.setAttribute('onclick', 'activeToggle(this)');
//         li.setAttribute('id', user);
//
//         var img = document.createElement('img');
//         var src = "starboot/assets/img/reddit-alien-brands.svg";
//         img.setAttribute('src', src);
//         img.setAttribute('alt', 'guest');
//
//         var aboutDiv = document.createElement('div');
//         aboutDiv.classList.add('about');
//         var name = document.createElement('div');
//         name.classList.add('name');
//         name.textContent = user;
//         aboutDiv.appendChild(name);
//
//         var statusDiv = document.createElement('div');
//         statusDiv.classList.add('status');
//         var icon = document.createElement('i');
//         icon.setAttribute('id', 'master-status-icon');
//         icon.classList.add('fa', 'fa-circle', 'online');
//         var span = document.createElement('span');
//         span.setAttribute('id', 'master-status-content');
//         span.textContent = 'online';
//         statusDiv.appendChild(icon);
//         statusDiv.appendChild(span);
//
//         aboutDiv.appendChild(statusDiv);
//
//         li.appendChild(img);
//         li.appendChild(aboutDiv);
//
//         var alertDiv = document.createElement('div');
//         alertDiv.classList.add('circle', 'd-flex', 'align-items-center', 'justify-content-center', 'd-none');
//         aspan = document.createElement('span');
//         aspan.classList.add('staging-count');
//         alertDiv.appendChild(aspan);
//         li.appendChild(alertDiv);
//
//         onlineUser.appendChild(li);
//     }
// };
//
// // delete outOne from onlineList
// function deleteOnlieList(outOne) {
//     var element =  document.getElementById(outOne);
//     element.parentNode.removeChild(element);
// }
//
// // insert division of receiver
// function divideChatSection(receiverId){
//     var div = document.createElement('div');
//     div.classList.add('clearfix');
//     var str = receiverId + '님과의 대화 시작 ';
//     var hr = document.createElement('hr');
//
//     div.textContent = str;
//     div.appendChild(hr);
//     var chatContent =  document.querySelector("#chat-content");
//     chatContent.appendChild(div);
//
//     scrollDown();
// };
//
// // update master status
// function updateMasterStatus(){
//     if(preMasterStatus != masterStatus) {
//         var icon = document.getElementById('master-status-icon');
//
//         if(masterStatus == "online") {
//             icon.classList.remove('offline');
//             icon.classList.add('online');
//         } else {
//             icon.classList.remove('online');
//             icon.classList.add('offline');
//         }
//         masterStatusContent.innerHTML = masterStatus;
//     }
// };
//
// // save chat history
// function setChatHistory(name){
//     var value =[];
//     document.querySelectorAll('.message-li').forEach(item => {
//
//         var time = item.querySelector('.message-data > .message-data-time').textContent;
//         var message = item.querySelector('.message').textContent;
//         var senderId;
//         var type = item.querySelector('.message').classList[1];
//         if(type == 'my-message'){
//             senderId = myId;
//         } else {
//             senderId = name;
//         }
//
//         data = {
//             "time":time,
//             "message":message,
//             "senderId":senderId
//         }
//         value.push(data);
//
//     })
//
//     sessionStorage.setItem(name, JSON.stringify(value));
// };
//
// // insert pre chat history
// function getChatHistory(name){
//     var data = JSON.parse(sessionStorage.getItem(name));
//
//     if(data != null) {
//         data.forEach(item => {
//             var time = item.time;
//             var message = item.message;
//             var senderId = item.senderId;
//
//             insertMessage(senderId, time, message);
//         })
//     }
// };
//
// // scroll down event
// function scrollDown() {
//     var chatContent =  document.querySelector("#chat-content");
//     chatContent.scrollTop = chatContent.scrollHeight;
// };
