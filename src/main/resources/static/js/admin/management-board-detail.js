$('.answer-button').on('mouseover', function(){
    $('.airplane').attr('src', '../../static/images/admin/changed-plane.png')
    $('.answer-button').css('border-color', '#006633b3');
})

$('.answer-button').on('mouseout', function(){
    $('.airplane').attr('src', '../../static/images/admin/paper-plane.png')
    $('.answer-button').css('border-color', '#00000029');
})

$('.answer-button').on('click', function(){
    $('#send-modal').css('display', 'flex');
});

$('#cancel-btn2').on('click', function(){
    $('#send-modal').hide();
});