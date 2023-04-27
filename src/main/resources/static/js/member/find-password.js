function showModal(){
    $('.modal-whole').css('display', 'flex');
}

function closeModal(){
    $('.modal-whole').css('display', 'none');
}

// 이메일 변수
const $emailInput = $(".input-text");
// 이메일 에러 변수
const $emailWarning = $(".error-text");
var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
const $emailInputvalue = $("#memberEmail");

//비밀번호 전송 버튼
const $submitBtn = $("#submit-btn");

console.log($submitBtn);
console.log($emailInputvalue);
console.log($('input[name=memberEmail]').val());

$submitBtn.on("click", function () {
    if ($emailInput.val()) {
        $submitBtn.attr("type", "submit");
    } else {
        $submitBtn.attr("type", "button");
    }
});



$emailInput.on("blur", function(){
    /* 이메일 중복 확인 */
    /* if (result.check) {
        $emailWarning.text("가입된 계정이 없습니다. 이메일을 다시 확인해주세요.");
        $emailWarning.css("display", "block");
        $emailInput.css("border-color", "#e52929");
        emailFlag = false;
    }  */if (!emailPattern.test($emailInput.val())) {
        $emailWarning.text("이메일을 입력해 주세요.");
        $emailWarning.css("display", "block");
        $emailInput.css("border-color", "#e52929");
    } else {
        $emailWarning.css("display", "none");
        $emailInput.css("border-color", "#e0e0e0");
    }
});

