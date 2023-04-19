/**
 * 
 */

// 비밀번호 변수
const $passwordInput = $("#newPassword");
// 비밀번호 에러 변수
const $passwordWarning = $("#passwordError");
let passwordFlag = false;
// 비밀번호 정규식 이벤트 사용 및 함수
$passwordInput.on("blur", function(){
    var $passwordInputValue = $passwordInput.val();
    var passwordInputValue = $passwordInput.val();
    // var num = passwordInputValue.search(/[0-9]/g);
    // var eng = passwordInputValue.search(/[a-z]/ig);
    // var spe = passwordInputValue.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
    
    var regExp= /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,20}$/;


    // $identificationInput.css("border-color", "#f66");
    // $identificationInput.css("border-color", "#dde2e6");
    if($passwordInputValue.length < 8){
        $passwordWarning.text("최소 8자입니다.");
        $passwordWarning.css("display", "block");
        $passwordInput.css("border-color", "#f66");
        passwordFlag = false;
    }else if($passwordInputValue.length > 20){
        $passwordWarning.text("최대 20자입니다.");
        $passwordWarning.css("display", "block");
        $passwordInput.css("border-color", "#f66");
        passwordFlag = false;
    }else if(!regExp.test($passwordInputValue)){
        console.log("들어옴");
        $passwordWarning.text("영문,숫자,특수문자를 조합한 8자 이상");
        $passwordWarning.css("display", "block");
        $passwordInput.css("border-color", "#f66");
        passwordFlag = false;
    }else {
        $passwordWarning.css("display", "none");
        $passwordInput.css("border-color", "#dde2e6");
        passwordFlag = true;
    }
    completeAllCheck();
});

// 비밀번호 확인 변수
const $passwordCheckInput = $("#newPasswordConfirm");
// 비밀번호 확인 에러 변수
const $passwordCheckWarning = $(".error-text");
let passwordCheckFlag = false;
// 비밀번호 확인 정규식 이벤트 사용 및 함수
$passwordCheckInput.on("blur", function(){
    var $passwordInputValue = $passwordInput.val();
    var $passwordCheckInputValue = $passwordCheckInput.val();

    // $passwordCheckInput.css("border-color", "#f66");
    // $passwordCheckInput.css("border-color", "#dde2e6");
    if($passwordCheckInputValue.length < 1){
        $passwordCheckWarning.text("동일한 비밀번호를 입력해주세요.");
        $passwordCheckWarning.css("display", "block");
        $passwordCheckInput.css("border-color", "#f66");
        passwordCheckFlag = false;
    } else if($passwordCheckInputValue == $passwordInputValue){
        $passwordCheckWarning.css("display", "none");
        $passwordCheckInput.css("border-color", "#dde2e6");
        passwordCheckFlag = true;
    } else {
        $passwordCheckWarning.text("동일한 비밀번호를 입력해주세요.");
        $passwordCheckWarning.css("display", "block");
        $passwordCheckInput.css("border-color", "#f66");
        passwordCheckFlag = false;
    }
    completeAllCheck();
});

	/*비밀번호 암호화*/
	$(".passwordval").val(btoa($(".passwordval").val()));
	$(".passwordvalconfirm").val(btoa($(".passwordvalconfirm").val()));

// 비밀번호 저장하기 버튼
const $formSubmit = $("#saveBtn");


const $completeButton = $("#saveBtn");
/* 모든 정규식 완료 및 전체 동의 체크 시 버튼 활성화 */
function completeAllCheck() {

    // let identificationFlag = false; // 아이디
    // let passwordFlag = false; // 비밀번호
    // let passwordCheckFlag = false; // 비밀번호 확인
    // let nicknameFlag = false; // 닉네임
    // let emailFlag = false; // 이메일
    // let nameFlag = false; // 이름
    // let phoneCheck = false; // 핸드폰
    
    if (passwordFlag && passwordCheckFlag) {
        console.log("완료");
        $completeButton.css("pointer-events", "auto");
        $completeButton.css("cursor", "pointer");
        // $completeButton.css("border-color", "#00c4c4");
        // $completeButton.css("background-color", "#00c4c4");
        $completeButton.css("opacity", "0.8");  // 활성화 opacity: 0.8;
        $completeButton.css("color", "#fff");
    } else {
        console.log("하나라도 실패 시 들어옴.")
        $completeButton.css("pointer-events", "none");
        $completeButton.css("cursor", "default");
        // $completeButton.css("border-color", "#00c4c4");
        // $completeButton.css("background-color", "#00c4c4");
        $completeButton.css("opacity", "0.45");  //  비활성화 opacity: 0.45;
        $completeButton.css("color", "#fff");
    }

}