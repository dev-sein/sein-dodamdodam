/* login */

// 아이디 변수
const $idInput = $('#id');
// 아이디 에러 변수
let $idWarning = $(".id-error");
$idInput.on("blur", function() {
	var $idInputValue = $idInput.val();
	var idInputValue = $idInput.val();

	if ($idInputValue.length < 1) {
		$idWarning.text("아이디를 입력해 주세요.");
		$idWarning.css("display", "block");
		$idInput.css("border-color", "#e52929");


	} else if ($idInputValue.length < 6) {
		$idWarning.text("6글자 이상의 영문자, 숫자, 특수기호(_)만 사용 가능합니다.");
		$idWarning.css("display", "block");
		$idInput.css("border-color", "#e52929");

	} else {
		$idWarning.css("display", "none");
		$idInput.css("border-color", "#e0e0e0");
	}
})


// 비밀번호 변수
const $passwordInput = $("#password");
// 비밀번호 에러 변수
let $passwordWarning = $(".pwd-error");


// 비밀번호 정규식 이벤트 사용 및 함수
$passwordInput.on("blur", function() {
	var $passwordInputValue = $passwordInput.val();
	var passwordInputValue = $passwordInput.val();

	var regExp = /^[A-Za-z\d@$!%*#?&]{8,32}$/;


	if ($passwordInputValue.length < 1) {
		$passwordWarning.text("비밀번호를 입력해주세요.");
		$passwordWarning.css("display", "block");
		$passwordInput.css("border-color", "#e52929");
		passwordFlag = false;
	} else if ($passwordInputValue.length > 31) {
		$passwordWarning.text("글자수를 초과하였습니다.");
		$passwordWarning.css("display", "block");
		$passwordInput.css("border-color", "#e52929");
		passwordFlag = false;
	} else if (!regExp.test($passwordInputValue)) {
		console.log("들어옴");
		$passwordWarning.text("8글자 이상 입력해 주세요.");
		$passwordWarning.css("display", "block");
		$passwordInput.css("border-color", "#e52929");
		passwordFlag = false;
	} else {
        $passwordWarning.css("display", "none");
		$passwordInput.css("border-color", "#e0e0e0");
		passwordFlag = true;
	}
});


$submitBtn.on('click', function(){
    if($idInput && $passwordInput){
        $passwordInput.val(btoa($passwordInput.val()));
		document.loginForm.submit();
	}else{
        $passwordWarning.text("도담도담에 등록되지 않은 아이디거나, 아이디 또는 비밀번호가 일치하지 않습니다.");
        $passwordInput.css("border-color", "#e52929");

    }
})