/* 신청 유형 선택, 개인과 기업  */
$(function(){
    $('.personal').click(function(){
    $('#personal-span').css("color","#00c4c4");
    $('#business-span').css("color","#495057");
    $('.business-container').hide();
    });
    $('#business-btn').click(function(){
    $('#personal-span').css("color","#495057");
    $('#business-span').css("color","#00c4c4");
    $('.business-container').show();
    });
    });

    