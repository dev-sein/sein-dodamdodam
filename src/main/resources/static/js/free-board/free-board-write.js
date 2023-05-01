 // 초기 등록 버튼 색상 설정
$('#regist_btn').css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242,244,247)");

// textarea 입력시 등록 버튼 색상 변경
$('#content_textarea, #title_textarea').on('input', function() {
  var contentLength = $('#content_textarea').val().length;
  var titleLength = $('#title_textarea').val().length;
  var btnType = $('.btn-type');
  if (contentLength > 0 && titleLength > 0 && btnType.hasClass("active")) {
    $('#regist_btn').css("color", "white").css("background-color", "#006633");
  } else {
    $('#regist_btn').css("color", "rgb(196, 196, 196)").css("background-color", "rgb(242,244,247)");
  }
});

/* 카테고리 버튼 이벤트 */
$(".btn-type").on("click", function() {
    console.log(this)
    $(".btn-type").each((i, v) => {
        console.log("들어옴2")
        if($(v).hasClass("active")) {
            $(v).removeClass("active");
        }
    });
    
    if(!($(this).hasClass("active"))) {
        console.log("들어옴3")
        $(this).addClass("active");
    }
});

/* 취소버튼 눌렀을 때 - 모달 */
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

/* 파일 썸네일 */
/* 파일인풋 */
const file = document.querySelector('input[type=file]');
const imgButton = document.querySelector("#plus_button");
console.log(imgButton);

function handleFiles(files) {
    /* 썸네일 담을 div의 부모 */
    const thumbnailList = document.getElementById("thumbnail-list");

    for (let i = 0; i < files.length; i++) {

        /* 파일절대경로얻기 */  
        const file = files[i];
        const reader = new FileReader();
        /* reader가 onload 할때 */
        reader.onload = function(event) {
            /* 썸네일 담을 div와 그 자식의 span 선언 */   
            const thumbnail = document.createElement("div");
            const thumbnailSpan = document.createElement("span");


            let result = event.target.result;

            /* 썸네일 담을 div와 그 자식의 span에 썸네일 css와 x버튼 css 추가*/
            thumbnail.classList.add("imageThumbnail");
            thumbnailSpan.classList.add("closeImgButton");

            /* 썸네일 담을 div에 절대경로 넣어주기 */
            thumbnail.style.backgroundImage = `url('${result}')`;

            /* 썸네일 담을 div와 그 자식의 span 추가해주기 */
            thumbnailList.prepend(thumbnail);
            thumbnail.appendChild(thumbnailSpan);

            /* x버튼 선언 */
            const closeButton = document.querySelector(".closeImgButton");

            /* x버튼 누를 시 x버튼과 backgroundImage 지워주기 */
            closeButton.addEventListener('click', function (e) {
                e.preventDefault();
                file.value = "";
                this.style.display = 'none';
                thumbnail.style.backgroundImage = `url('')`;
                thumbnail.remove(thumbnail);
                $("#plus_picture").show();
            });

            /* 파일 개수가 8개 이상이면 버튼숨기기 */
           if($(".imageThumbnail").length > 2 ){
            $("#plus_picture").hide();
            return;
           }
            
        };
        /* result 속성(attribute)에 담기 */
        reader.readAsDataURL(file);
           
    }

}

/* 버튼을 감싸고있는 label객체 들고오기 */
const fileInput = document.getElementById("photo-picker");

/* 버튼을 감싸고있는 label객체 클릭하면 위에 function handleFiles 실행 */
fileInput.addEventListener("change", function(event) {
    handleFiles(event.target.files);
});
