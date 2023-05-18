$('#chat_modal').hide();
$('#chat_room').hide();
// $('#chat_room').show();

$('.channel_chat_container').on('click', function(){
    $('#chat_modal').fadeIn(500);
});

$('.close_modal_container').on('click', function(){
    $('#chat_modal').fadeOut(300);
});

$('.chat_li').each((i, e) => {
    console.log("들어옴")
    $(e).on('click', function(){
        $('#chat_room').show();
    })
})

$('.back_list_container').on('click', function(){
    $('#chat_modal').show();
    $('#chat_room').hide();
})

$(document).on('keydown', 'textarea.input_msg', function(e){
    if(e.keyCode == 13 && !e.shiftKey) {
        e.preventDefault();
        const message = $(this).val();

        // 입력창 clear
        clearTextarea();
    }
});
 
// 메세지 입력박스 내용 지우기
function clearTextarea() {
    $('textarea.input_msg').val('');
}
 
$(document).ready(function() {
    /* 스크롤바 항상 아래로 내리기 */
    $('#chat_room #chat_modal_li_container').scrollTop($('#chat_room #chat_modal_li_container')[0].scrollHeight);
})

/**************************************************************************************************************/

const $createChat = $(".create_chat");
const $sendBtn = $(".msg_submit_button");
const $inputMsg = $(".input_msg");

// 웹소켓 생성
const socket = new WebSocket("ws://localhost:10000/ws/chat");

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