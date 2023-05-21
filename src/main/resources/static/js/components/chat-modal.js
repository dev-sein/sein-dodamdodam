// $('#chat_modal').hide();
// $('#chat_room').hide();
// // $('#chat_room').show();
//
// $('.channel_chat_container').on('click', function(){
//     $('#chat_modal').fadeIn(500);
// });
//
// $('.close_modal_container').on('click', function(){
//     $('#chat_modal').fadeOut(300);
// });
//
// $('.chat_li').each((i, e) => {
//     $(e).on('click', function(){
//         $('#chat_room').show();
//     })
// })
//
// $('.back_list_container').on('click', function(){
//     $('#chat_modal').show();
//     $('#chat_room').hide();
// })
//
//
//
//
// $(document).on('keydown', 'textarea.input_msg', function(e){
//     if(e.keyCode == 13 && !e.shiftKey) {
//         e.preventDefault();
//         const message = $(this).val();
//
//         // 입력창 clear
//         clearTextarea();
//     }
// });
//
// // 메세지 입력박스 내용 지우기
// function clearTextarea() {
//     $('textarea.input_msg').val('');
// }
//
// $(document).ready(function() {
//     /* 스크롤바 항상 아래로 내리기 */
//     $('#chat_room #chat_modal_li_container').scrollTop($('#chat_room #chat_modal_li_container')[0].scrollHeight);
// })
