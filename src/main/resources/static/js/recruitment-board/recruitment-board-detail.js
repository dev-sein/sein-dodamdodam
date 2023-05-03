/* 클릭 시 수정/삭제 버튼 창 */
function showList(e){
    if($(e).next().css('display') == 'none'){
        $(e).next().show();
    } else {
        $(e).next().hide();
    }
}

/* 수정버튼 눌렀을 때 */
$(".modify-button").each((i, e) => {
    $(e).click(() => {
        $($(".modify-textarea")[i]).show();//수정영역
        $('.comment-util-list').hide();//수정,삭제 모달 숨기기
        $($('.comment-util')[i]).attr("disabled",true);//수정,삭제 버튼 비활성화
        $($(".comment-content")[i]).css("display","none");//기존영역 숨기기
        $($(".comment-date")[i]).css("display","none");//날짜 숨기기
        $($(".comment-bottom")[i]).css("display","block");//취소,수정완료 버튼
    });
});

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


/* 좋아요 */
$(function(){
    $('.wish-button').click(function(e){
        if($('.wish-button').attr('aria-pressed') == 'false'){
            $(".heart-icon").attr("src", "../../static/images/recruitment-board/heart-background.png");
            $('.wish-button').attr('aria-pressed','true') //하트 색 채우기
            $('.like-cancel-text').hide(); //해제 문구
            $('#like-modal').fadeIn();
            $('.like-text').fadeIn();
            // $('#like-modal').css({right: '-30%'}); //오->왼 슬라이드 등장
            // $('#like-modal').animate({right:'30px'}, {duration: 700}); //오->왼 슬라이드 등장
            // $('#like-modal').show(); //슬라이드 보이기
            // $('.like-text').show(); //찜 추가 문구
            $('#like-modal').delay(500).fadeOut(); //사라지기
        
        }else{
            $(".heart-icon").attr("src", "../../static/images/recruitment-board/non-heart-icon.png");
            $('.wish-button').attr('aria-pressed','false') //색 비우기
            $('.like-text').hide();  //찜 추가 문구
            // $('#like-modal').css({right: '-30%'}); //오->왼 슬라이드 등장
            // $('#like-modal').animate({right:'30px'}, {duration: 700}); //오->왼 슬라이드 등장
            $('#like-modal').fadeIn();
            $('.like-cancel-text').fadeIn(); //찜 해제 문구
            // $('#like-modal').show(); //슬라이드 보이기
            // $('.like-cancel-text').show(); //찜 해제 문구
            $('#like-modal').delay(500).fadeOut(); //사라지기
        }
    });
});


/* 신청 완료 모달 */
function popOpen() {
var modalPop = $('.modal-wrap');
var modalBg = $('.modal-bg'); 
    $(modalPop).show();
    $(modalBg).show();
}
function popClose() {
var modalPop = $('.modal-wrap');
var modalBg = $('.modal-bg');
    $(modalPop).hide();
    $(modalBg).hide();
}

/* 복사 완료 모달 */
function popCopyOpen() {
var modalPop = $('.modal-copy');
var modalBg = $('.modal-bg'); 
    $(modalPop).show();
    $(modalBg).show();
}
function popCopyClose() {
var modalPop = $('.modal-copy');
var modalBg = $('.modal-bg');
    $(modalPop).hide();
    $(modalBg).hide();
}



/* url 복사 */
function clip(){
var url = '';
var textarea = document.createElement("textarea");
    document.body.appendChild(textarea);
    url = window.document.location.href;
    textarea.value = url;
    textarea.select();
    document.execCommand("copy");
    document.body.removeChild(textarea);
}

/* top 버튼  */
var topEle = $('.top-btn');
var delay = 400;

/* top show-hide */
$(window).scroll(function() {
    if($(this).scrollTop() > 200 ) {
        console.log(topEle);
		$('.top-btn').fadeIn();
	} else {
		$('.top-btn').fadeOut();
	}
});

/* top버튼- 위로 올리기 */
$('.top-btn').click( function() {
	$('html, body').animate({scrollTop:0}, delay );
	return false;
});