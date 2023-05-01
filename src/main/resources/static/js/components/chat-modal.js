$('#chat_modal').hide();
$('#chat_room').hide();

$('.channel_chat_container').on('click', function(){
    $('#chat_modal').fadeIn(500);
});

$('.close_modal_container').on('click', function(){
    $('#chat_modal').fadeOut(300);
});

$('.chat_li').each((i, e) => {
    $(e).on('click', function(){
        $('#chat_room').show();
    })
})

$('.back_list_container').on('click', function(){
    $('#chat_modal').show();
    $('#chat_room').hide();
})