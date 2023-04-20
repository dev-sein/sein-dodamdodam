// /*myId.js */
// /*닉네임 변경 AJAX*/


// /*이메일 인증 버튼*/
// const $emailChangeBtn = $("#emailChangeBtn");
// const $inputEmail = $("#userEmail");
// // 이메일 에러 변수
// const $emailWarning = $("#emailError");
// let emailFlag = false;

// /* 이메일 변경 버튼 클릭 시 변경*/
// $("#emailChangeBtn").on("click", $("#emailChangeBtn"), function(){
// 	$('#userEmail').val('');
// 	$('#emailChangeBtn').css('display','none');
// 	$('#emailCheckBtn').css('display','block');
// 	$('#emailChangeBtn').css('borderColor', "#00c4c4");
//    	$("#userEmail").attr("disabled", false, "placeholder", "이메일 계정");
// 	$("#userEmail").css('backgroundColor','#fff', 'borderColor', "#00c4c4");
// /*	$("#changeEmailInfo").text('로그인 아이디와 안내 메일 발송 주소가 변경됩니다.');*/
// 	$("#changeEmailInfo").css('display','block');
// });

// // 이메일 정규식 이벤트 사용 및 함수
// $inputEmail.on("blur", function() {
//     var $emailInputVal = $inputEmail.val();
//     var emailInputVal = $inputEmail.val();
//     var $emailWarningVal = $emailWarning.val();

//     var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

//     if($emailInputVal.length < 1){ 
//         $emailWarning.text("이메일을 입력해주세요.");
//         $emailWarning.css("display", "block");
//         $inputEmail.css("border-color", "#f66");
//         emailFlag = false;

//     } else if(!emailPattern.test($emailInputVal)) {
//         /*$emailWarning.text("이메일 형식이 올바르지 않습니다.");*/
//         $("#emailError").css('display','block');
//         $inputEmail.css("border-color", "#f66");
//         emailFlag = false;
//     } else {
//         $emailWarning.css("display", "none");
//         $inputEmail.css("border-color", "#dde2e6");
//         emailFlag = true;
//     }
//     completeAllCheck();
// });


// /*휴대폰 번호 변경*/

// /*mobileChangeBtn
// mobileNumber input phone
// mobileNumberError 유효한 전화번호를 입력해주세요
// */

// /* 이메일 변경 버튼 클릭 시 변경*/
// $("#mobileChangeBtn").on("click", $("#mobileChangeBtn"), function(){
// 	$('#mobileNumber').val('');
// 	$('#mobileChangeBtn').css('display','none');
// 	$('#mobileCheckBtn').css('display','block');
// 	$('#mobileChangeBtn').css('borderColor', "#00c4c4");
//    	$("#mobileNumber").attr("disabled", false, "placeholder", "이메일 계정");
// 	$("#mobileNumber").css('backgroundColor','#fff', 'borderColor', "#00c4c4");
// /*	$("#changeEmailInfo").text('로그인 아이디와 안내 메일 발송 주소가 변경됩니다.');*/
// 	$("#mobileNumberError").css('display','block');
// });


// /*이메일 인증 버튼*/
// const $mobileChangeBtn = $("#mobileChangeBtn");
// // 이메일 에러 변수
// const $phoneWarning = $("#mobileNumberError");
// //핸드폰 변수
// const $phoneInput = $('#mobileNumber');
// //핸드폰 에러 변수
// /*const $phoneWarning = $('.phone-error');*/
// let phoneFlag = false;
// // 핸드폰 정규식 이벤트 함수 사용
// $phoneInput.on("blur", function() {
//     var isPhoneNum = /([01]{2,})([01679]{1,})([0-9]{3,4})([0-9]{4})/;
//     var $phoneInputVal = $phoneInput.val();
//     var phoneInputVal = $phoneInput.val();


//     // $phoneInput.css("border-color", "#f66");
//     // $phoneInput.css("border-color", "#dde2e6");
//     if($phoneInputVal.length < 1) {
//         $phoneWarning.text("핸드폰 번호를 입력해주세요.");
//         $phoneWarning.css("display", "block");
//         $phoneInput.css("border-color", "#f66");
//         phoneFlag = false;
//     } else if(!isPhoneNum.test($phoneInputVal)) {
//         $phoneWarning.text("잘못된 형식입니다. 다시 입력해주세요.");
//         $phoneWarning.css("display", "block");
//         $phoneInput.css("border-color", "#f66");
//         phoneFlag = false;
//     } else {
//         $phoneWarning.css("display", "none");
//         $phoneInput.css("border-color", "#dde2e6");
//         phoneFlag = true;
//     }

//     completeAllCheck();
// });

