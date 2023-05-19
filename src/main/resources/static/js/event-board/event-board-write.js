/* 신청 유형 선택, 개인과 기업  */
$(function(){
    $('.personal').click(function(){
        $('#personal-span').css("color","#006633");
        $('#business-span').css("color","#495057");
        $('.business-container').hide();
    });
    $('#business-btn').click(function(){
        $('#personal-span').css("color","#495057");
        $('#business-span').css("color","#006633");
        $('.business-container').show();
    });
});

/* 주소 */
function searchAddress() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }

            } else {
            }

            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}


/* 필수사항, 정규식 */
$(function() {
    $("#submit-btn").validate();
    $.extend( $.validator.messages, {
        required: "필수 항목입니다.",
        number: "숫자만 입력하세요.",
        email: "유효하지 않은 이메일 주소입니다."
    });
});

$('.submit-btn').on('click', function () {
    document.eventForm.submit();
})


/* 파일 */
let sel_files = [];  // 전역 변수로 이동

( /* att_zone : 이미지들이 들어갈 위치 id, btn : file tag id */
    imageView = function imageView(att_zone, btn){

        var attZone = document.getElementById(att_zone);
        var btnAtt = document.getElementById(btn)

        // 이미지와 체크 박스를 감싸고 있는 div 속성
        var div_style = 'display:inline-block;position:relative;'
            + 'width:120px;height:120px;margin:5px;z-index:1;';
        // 미리보기 이미지 속성
        var img_style = 'width:100%;height:100%;z-index:none';
        // 이미지안에 표시되는 체크박스의 속성
        var chk_style = 'width:15px;height:15px;position:absolute;font-size:4px;padding:0px;'
            + 'right:2px;bottom:3px;z-index:999;color:#fff;cursor:pointer;border-radius:50%;background-color:darkgray;border:none;';

        btnAtt.onchange = function(e){
            var files = e.target.files;
            var fileArr = Array.prototype.slice.call(files)
            for(f of fileArr){
                imageLoader(f);
            }
        }

        /*첨부된 이미지들을 배열에 넣고 미리보기 */
        imageLoader = function(file){
            sel_files.push(file);
            var reader = new FileReader();
            reader.onload = function(ee){
                let img = document.createElement('img')
                img.setAttribute('style', img_style)
                img.src = ee.target.result;
                attZone.appendChild(makeDiv(img, file));
            }
            reader.readAsDataURL(file);
        }

        /*첨부된 파일이 있는 경우 checkbox와 함께 attZone에 추가할 div를 만들어 반환 */
        makeDiv = function(img, file){
            var div = document.createElement('div')
            div.setAttribute('style', div_style)

            var btn = document.createElement('input')
            btn.setAttribute('type', 'button')
            btn.setAttribute('value', 'x')
            btn.setAttribute('delFile', file.name);
            btn.setAttribute('style', chk_style);
            btn.onclick = function(ev){
                var ele = ev.srcElement;
                var delFile = ele.getAttribute('delFile');
                for(var i=0 ;i<sel_files.length; i++){
                    if(delFile== sel_files[i].name){
                        sel_files.splice(i, 1);
                    }
                }

                if($('#att_zone').children().length > 5){
                    $(".file-button").hide();
                    return;
                }

                dt = new DataTransfer();
                for(f in sel_files) {
                    var file = sel_files[f];
                    dt.items.add(file);
                }
                btnAtt.files = dt.files;
                var p = ele.parentNode;
                attZone.removeChild(p)
            }
            div.appendChild(img)
            div.appendChild(btn)
            return div
        }
    }
)('att_zone', 'btnAtt')

$(document).ready(function(){
    $("#btnAtt").on("change", function(){ // input type file 변경 이벤트
        var formData = new FormData();
        var inputFile = $("input[name='eventFiles1']");
        var files = inputFile[0].files;

        for(var i = 0; i < files.length; i++){
            formData.append("file", files[i]);
        }

        $.ajax({
            url: '/file/upload',
            processData: false, // 필수
            contentType: false, // 필수
            data: formData,
            type: 'POST',
            dataType: 'json',
            success: function(result){
                alert("파일이 성공적으로 업로드 되었습니다.");
                //필요한 경우 result 값을 사용하여 성공 처리를 수행합니다.
            },
            error: function(e){
                alert("파일 업로드에 실패하였습니다.");
                //서버 에러를 처리합니다.
            }
        });
    });

    $(".submit-btn").click(function(e){
        e.preventDefault();
        // 다른 폼 요소 검증
        $("#submit-btn").submit(); // 폼 전송
    });
});
