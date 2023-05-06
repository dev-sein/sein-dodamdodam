/*사진 첨부*/
function setThumbnail(event) {
    var reader = new FileReader();

    reader.onload = function(event) {
      var img = document.createElement("img");
      if(document.querySelector("#image_container").childElementCount == 0 ){
        img.setAttribute("src", event.target.result);
        img.setAttribute("id", "review-image");
        document.querySelector("div#image_container").appendChild(img);
        // 테스트
        document.querySelector(".MainImage_wrapper").style.display = "none";
      } else {
        var child = document.querySelector("div#image_container img");
        document.querySelector("div#image_container").removeChild(child);
        img.setAttribute("src", event.target.result);
        img.setAttribute("id", "review-image");
        document.querySelector("div#image_container").appendChild(img);
      }
    };

    reader.readAsDataURL(event.target.files[0]);
  }

  document.querySelector("#image_container").addEventListener("click", function(){
      var child = document.querySelector("div#image_container img");  // 만들어진 자식
      document.querySelector("div#image_container").removeChild(child); // 만들어진 이미지 삭제
      document.querySelector("#image").value = null;      // input type:file 에 들어간 이미지 비워주기
      // 테스트
      document.querySelector(".MainImage_wrapper").style.display = "flex";  // 없어진 이미지 넣기 버튼 다시 보여주기
  });


document.querySelector("#image").addEventListener("click", function() {
  document.querySelector("#review-placeholder").style.display = "none";
});

// 미리보기
document.querySelector("#image").addEventListener("change", function(event){
  var reader = new FileReader();
  reader.onload = function(event) {
    var img = document.createElement("img");
    if(document.querySelector("#image_container").childElementCount == 0 ){
      img.setAttribute("src", event.target.result);
      img.setAttribute("id", "review-image");
      document.querySelector("div#image_container").appendChild(img);
      // 테스트
      document.querySelector(".MainImage_wrapper").style.display = "none";
    } else {
      var child = document.querySelector("div#image_container img");
      document.querySelector("div#image_container").removeChild(child);
      img.setAttribute("src", event.target.result);
      img.setAttribute("id", "review-image");
      document.querySelector("div#image_container").appendChild(img);
    }
  };
  reader.readAsDataURL(event.target.files[0]);

  document.querySelector("#review-placeholder").style.display = "none";
});


function removeImage() {
  var img = document.querySelector(".review-photo");
  img.parentNode.removeChild(img);
  var input = document.querySelector("#image");
  input.value = null;
}

function openImageSelector() {
  var input = document.querySelector("#image");
  input.click();
}



// 별점
const ratingInputs = document.querySelectorAll('.rating input');
const ratingLabels = document.querySelectorAll('.rating label');

ratingInputs.forEach((input) => {
  input.addEventListener('click', () => {
    const checkedValue = input.value;
    const checkedIndex = parseInt(checkedValue) - 1;

    for (let i = 0; i <= 4; i++) {
      if (i <= checkedIndex) {
        ratingLabels[i].classList.add('checked');
      } else {
        ratingLabels[i].classList.remove('checked');
      }
    }
  });
});


// 리뷰 작성 확인 및 제출
$(".registButton_button").on('click', function(e){
  e.preventDefault();
  let flag1 = true;
  let flag2 = true;
  let flag3 = true;
  let flag4 = true;

  console.log("들어옴");
  var $title = $("#input-title");
  var $content = $("#input-content");
  var $star = $("input[name=reviewGrade]:checked");
  const $imageInput = $('#image');

  console.log("star : " + $star.val());
  if($title.val().length < 1){
    // alert("제목을 작성해주세요.");
    $(".modal-wrapper").css('display', 'block');
    $(".modal-message").text("제목을 작성해주세요.");
    $("#input-title").focus();
    flag1 = false;
  } else if($content.val().length < 1){
    // alert("내용을 작성해주세요.");
    $(".modal-wrapper").css('display', 'block');
    $(".modal-message").text("내용을 작성해주세요.");
    $("#input-content").focus();
    flag2 = false;
  } else if($star.val() < 1 || $star.val() == null){
    // alert("만족도를 체크해주세요.");
    $(".modal-wrapper").css('display', 'block');
    $(".modal-message").text("만족도를 체크해주세요.");
    $star.focus();
    flag3 = false;
  }else if($imageInput.val().length < 1){
    $(".modal-wrapper").css('display', 'block');
    $(".modal-message").text("이미지를 입력해주세요.");
    $imageInput.focus();
    flag4 = false;
  }
  
  if(flag1 && flag2 && flag3 && flag4){
    // alert("완료");
    document.reviewForm.submit();
  }

})

function cancelWriteReview() {
  window.location.href = "/review/review-list";
}


$(".modal-close-btn").on("click", function(){
  $(".modal-wrapper").css('display', 'none');
})





