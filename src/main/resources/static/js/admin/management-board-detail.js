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

// var inquiryId = $('#inquiryId').val();
// var answer = $('#answer').val(); // 답변 내용

$('#confirm-btn2').click(function() {
    console.log("들어옴");
    $('#send-modal').css('display', 'none');
    var inquiryId = $('#inquiryId').val();
    var answer = $('#inquiryAnswer').val(); // 답변 내용
    // $('#inquiryForm').attr('action', '/admins/inquiry/detail/' + inquiryId);
    document.inquiryForm.submit();
    $('#inquiryForm').submit();
    console.log("제출됨");
});

$('#inquiryForm').on('submit', function(event) {
    var form = event.target;
    var id = form.getAttribute("data-id");
    form.action = form.action.replace("{id}", id);
});
