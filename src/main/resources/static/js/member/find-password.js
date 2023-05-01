/* 모달  */
function showModal(){
    $('.modal').css('display', 'block');
}

function closeModal(){
    $('.modal').css('display', 'none');
}

// 이메일
const $emailInput = $(".input-email .input-text");
const $emailError = $(".error");
const $emailInputvalue = $("#memberEmail");

// 이메일 정규식 이벤트 사용 및 함수
$emailInput.on("blur", function(){
    // 이메일 정규식
    var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    
    /* 이메일 중복 확인 */
    if (!emailPattern.test($emailInput.val())) {
        $emailError.text("이메일 형식이 올바르지 않습니다.");
        $emailError.css("display", "block");
        $emailInput.css("border-color", "#e52929");
    } else {
        $emailError.css("display", "none");
        $emailInput.css("border-color", "#e0e0e0");
    }
});

function checkEmail() {
	var email = $('#memberEmail').val(); //email 입력란의 값을 저장
	$.ajax({
		url: './checkEmail', //Controller에서 요청 받을 주소
		type: 'post', //POST 방식으로 전달
		data: {memberEmail: $emailInputvalue.val()},
		success: function (duplicateEmail) { //컨트롤러에서 넘어온 cnt값을 받는다
			if (duplicateEmail == 0) { //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 이메일
				$('.email_ok').css("display", "inline-block");
				$('.email_already').css("display", "none");
			} else { // cnt가 1일 경우 -> 이미 존재하는 이메일
				$('.email_already').css("display", "inline-block");
				$('.email_ok').css("display", "none");
			}
		},
		error: function () {
			//alert("에러입니다");
		}
	});
};
