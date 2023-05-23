
/* 클릭 시 수정/삭제 버튼 창 */
function showButton(e){
    if($(e).next().css('display') == 'none'){
        $(e).next().show();
    } else {
        $(e).next().hide();
    }
}

function showReplyUpdate(element) {
    var $parent = $(element).closest('.comment-wrap');
    $parent.find('.update-form').show();
    // $parent.find('.modify-textarea').show();
    $parent.find('.comment-bottom').show();
}

/* 수정버튼 눌렀을 때 */
// $(".modify-button").each((i, e) => {
//     $(e).click(() => {
//         $($(".modify-textarea")[i]).show();//수정영역
//         $('.comment-util-list').hide();//수정,삭제 모달 숨기기
//         $($('.comment-util')[i]).attr("disabled",true);//수정,삭제 버튼 비활성화
//         $($(".comment-content")[i]).css("display","none");//기존영역 숨기기
//         $($(".comment-date")[i]).css("display","none");//날짜 숨기기
//         $($(".comment-bottom")[i]).css("display","block");//취소,수정완료 버튼
//     });
// });

/* 삭제버튼 눌렀을 때 - 모달 */
function showModal(){
    $('.modal-copy').css('display', 'block');
    $('.modal-bg').css('display', 'block');
    $('body').css('overflow', 'hidden');
}

function closeModal(){
    $('.modal-copy').css('display', 'none');
    $('.modal-bg').css('display', 'none');
    $('body').css('overflow', 'visible');
}

/* 취소버튼 - 원래 상태로 복구 */
$(".modify-cancel").each((i, e) => {
    $(e).click(() => {
        $($(".modify-textarea")[i]).hide();//수정영역
        $('.comment-util-list').hide();//수정,삭제 모달 숨기기
        $($(".comment-content")[i]).css("display","block");//기존영역 숨기기
        $($(".comment-date")[i]).css("display","block");//날짜 숨기기
        $($(".comment-bottom")[i]).css("display","none");//취소,수정완료 버튼
    });
});



/* 클릭 했을 때 색 변경/취소 */
$(".btn-like").click(() => {
    if(!$(".btn-like").hasClass("active-heart-button")){
        /* 여기에변경될 요소 */
        $(".none-heart").hide();
        $(".active-heart").show();
        $(".btn-like").addClass("active-heart-button");
    }else {
        /* 원래 요소 */
        $(".none-heart").show();
        $(".active-heart").hide();
        $(".btn-like").removeClass("active-heart-button");
    }
});

/* 카카오톡 공유하기 API */
function shareMessage() {
Kakao.Share.sendDefault({
    objectType: 'feed',
    content: {
    title: '리바운드 보러갈 사람?',
    description: '#리바운드 #슬램덩크 #영화 #스포 #강백호 #농구',
    imageUrl:
        'http://127.0.0.1:5500/static/images/free-board/rebound.jpg',
    link: {
        // [내 애플리케이션] > [플랫폼] 에서 등록한 사이트 도메인과 일치해야 함
        mobileWebUrl: 'http://127.0.0.1:5500',
        webUrl: 'http://127.0.0.1:5500',
    },
    },
    social: {
    likeCount: 286,
    commentCount: 45,
    sharedCount: 845,
    },
    buttons: [
    {
        title: '웹으로 보기',
        link: {
        mobileWebUrl: 'https://developers.kakao.com',
        webUrl: 'https://developers.kakao.com',
        },
    },
    {
        title: '앱으로 보기',
        link: {
        mobileWebUrl: 'https://developers.kakao.com',
        webUrl: 'https://developers.kakao.com',
        },
    },
    ],
});
}
