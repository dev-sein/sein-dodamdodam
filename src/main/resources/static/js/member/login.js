/* login */

const $id = $("input#id");
const $password = $("input#password");


function send(){
    if(!$id.val()){
        showWarnModal("아이디를 입력해주세요!");
        $id.next().fadeIn(500);
        return;
    }
    if(!$password.val()){
        showWarnModal("비밀번호를 입력해주세요!");
        $password.next().fadeIn(500);
        return;
    }
	/*비밀번호 암호화*/
	$password.val(btoa($password.val()));
    document.login.submit();
}

$id.on("blur", function(){
    $id.next().hide();
    if($id.val()){
        $id.next().fadeIn(500);
        showHelp($id, "pass.png");
    }
});

$password.on("blur", function(){
    $password.next().hide();
    if($password.val()){
        $password.next().fadeIn(500);
        showHelp($password, "pass.png");
    }
});

$password.on("blur", function(){

});

function showHelp($input, fileName){
    $input.next().attr("src", "/static/images/member" + fileName);

    if(fileName == "pass.png") {
        $input.css("border", "1px solid rgba(0, 0, 0, 0.1)");
        $input.css("background", "rgb(255, 255, 255)");
        $input.next().attr("width", "18px");
    }else {
        $input.css("border", "1px solid rgb(255, 64, 62)");
        $input.css("background", "rgb(255, 246, 246)");
    }
}