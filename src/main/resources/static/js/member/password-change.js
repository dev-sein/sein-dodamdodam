/* 모달  */
function showModal(){
    $('.modal').css('display', 'block');
}

function closeModal(){
    $('.modal').css('display', 'none');
}

// 비밀번호 변수
const $passwordInput = $(".input-password");
// 비밀번호 에러 변수
const $passwordError = $(".error-text");
let passwordFlag = false;
// 비밀번호 정규식 이벤트 사용 및 함수
$passwordInput.on("blur", function() {
	var $passwordInputValue = $passwordInput.val();
	var regExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,20}$/;
    if ($passwordInputValue.length < 8) {
		$passwordError.css("display", "block");
		$passwordError.css("color", "#f66");
		$passwordInput.css("border-color", "#f66");
        $(".check-icon img").css("display", "none")
		passwordFlag = false;
	} else if ($passwordInputValue.length > 20) {
		$passwordError.css("display", "block");
		$passwordError.css("color", "#f66");
		$passwordInput.css("border-color", "#f66");
        $(".check-icon img").css("display", "none")
		passwordFlag = false;
	} else if (!regExp.test($passwordInputValue)) {
		console.log("들어옴");
		$passwordError.css("display", "block");
        $passwordError.css("color", "#f66");
		$passwordInput.css("border-color", "#f66");
        $(".check-icon img").css("display", "none")
		passwordFlag = false;
	} else {
		$passwordError.css("display", "none");
		$passwordInput.css("border-color", "#dde2e6");
        $(".check-icon img").css("display", "block")
		passwordFlag = true;
	}
});

// 비밀번호 확인 변수
const $passwordCheckInput = $(".input-re-password");
// 비밀번호 확인 에러 변수
const $passwordCheckError = $(".re-error-text");
let passwordCheckFlag = false;
// 비밀번호 확인 정규식 이벤트 사용 및 함수
$passwordCheckInput.on("blur", function() {
	var $passwordInputValue = $passwordInput.val();
	var $passwordCheckInputValue = $passwordCheckInput.val();
    
	if ($passwordCheckInputValue.length < 1) {
        $passwordCheckError.css("display", "block");
		$passwordCheckError.css("color", "#f66");
		$passwordCheckInput.css("border-color", "#f66");
        $(".re-check-icon img").css("display", "none")
		passwordCheckFlag = false;
	} else if ($passwordCheckInputValue == $passwordInputValue) {
		$passwordCheckError.css("display", "none");
		$passwordCheckInput.css("border-color", "#dde2e6");
        $(".re-check-icon img").css("display", "block")
		passwordCheckFlag = true;
	} else {
        $passwordCheckError.css("display", "block");
		$passwordCheckError.css("color", "#f66");
		$passwordCheckInput.css("border-color", "#f66");
        $(".re-check-icon img").css("display", "none")
		passwordCheckFlag = false;
	}
	completeAllCheck();
});

function send() {
	/*비밀번호 암호화*/
	$("input[name='userPassword']").val(btoa($("input[name='userPassword']").val()));
	$("#password-check").val(btoa($("#password-check").val()));

	document.joinForm.submit();
}