/*board_community_write*/

// function setThumbnail(event) {
//     var reader = new FileReader();

//     reader.onload = function(event) {
//       var img = document.createElement("img");
//       img.setAttribute("src", event.target.result);
//       document.querySelector("div#image_container").appendChild(img);
//     };

//     reader.readAsDataURL(event.target.files[0]);
//   }

var maxFiles = 1;
var currentFiles = 0;

// 이미지 선택 후 프리뷰
function setThumbnail(e) {
    var imageContainer = document.getElementById("image_container");
    if (currentFiles < maxFiles) {
        var reader = new FileReader();
        reader.onload = function(e) {
            var img = new Image();
            img.src = e.target.result;
            img.style.width = "100%";
            img.style.height = "auto";
            var imgContainer = document.createElement("div");
            imgContainer.style.display = "inline-block";
            imgContainer.style.marginRight = "10px";
            imgContainer.style.position = "relative";
            var deleteButton = document.createElement("button");
            deleteButton.innerHTML = "X";
            deleteButton.style.position = "absolute";
            deleteButton.style.top = "0";
            deleteButton.style.right = "0";
            deleteButton.style.backgroundColor = "white";
            deleteButton.style.color = "red";
            deleteButton.style.border = "none";
            deleteButton.style.cursor = "pointer";
            deleteButton.addEventListener("click", function() {
                imageContainer.removeChild(imgContainer);
                currentFiles--;
            });
            imgContainer.appendChild(img);
            imgContainer.appendChild(deleteButton);
            imageContainer.appendChild(imgContainer);
            currentFiles++;
        };
        reader.readAsDataURL(e.target.files[0]);
    } else {
        alert("최대 1개의 이미지만 선택 가능합니다.");
    }
}

// "등록하기" 버튼 클릭 시 파일 선택
document.getElementById("photosubmit").addEventListener("click", function() {
    document.getElementById("image").click();
});

  
// textarea 변수
const titleData = document.querySelector('.titleData');
const contentData = document.querySelector('.contentData');

// 에러메세지 출력 변수
const titleWarning = document.querySelector("#title-warning");
const contentWarning = document.querySelector(".content-warning");

// 저장하기 버튼
const formSubmit = document.querySelector("#bottom-button");

console.log(titleData);
console.log(contentData);

formSubmit.addEventListener("click", function(){
var contentInputValue = document.querySelector(".titleData").value;
if(contentInputValue.length < 1){
    titleData.style.borderColor = "red";
    titleWarning.innerHTML = "필수 정보입니다.";
    titleWarning.style.display = "block";
      titleWarning.style.color = "red";
}
});

formSubmit.addEventListener("click", function(){
var contentInputValue = document.querySelector(".contentData").value;
if(contentInputValue.length < 1){
    contentData.style.borderColor = "red";
    contentWarning.innerHTML = "필수 정보입니다.";
    contentWarning.style.display = "block";
      contentWarning.style.color = "red";
}
});


// 기간선택
const Select = document.querySelector(".Select1");
Select.addEventListener("click", function(event) {
console.log(Select);
let content = document.querySelector(".content1");
if(content.style.display == 'none'){
    content.style.display = 'block';
}else{
    content.style.display = 'none';
}
});

/*
const $select = $(".Select1");
const $content = $(".content1");
$select.click(function(event){
if($content.css("display") == 'none') {
    $content.css("display", "block");
}else{
    $content.css("display", "none");
}

});
*/

// 패키지선택
const content = document.querySelector(".content1");
const DefaultValue = document.querySelector(".DefaultValue");
let Item1 = document.querySelector(".Item1");
let Item2 = document.querySelector(".Item2");
let Item3 = document.querySelector(".Item3");

Item1.addEventListener("click", function() {
console.log("들어옴");
DefaultValue.innerHTML = "1개월 패키지";
if(content.style.display == 'none'){
    content.style.display = 'block';
}else{
    content.style.display = 'none';
}	
});

Item2.addEventListener("click", function() {
console.log("들어옴");
DefaultValue.innerHTML = "2개월 패키지";
if(content.style.display == 'none'){
    content.style.display = 'block';
}else{
    content.style.display = 'none';
}	
});

Item3.addEventListener("click", function() {
console.log("들어옴");
DefaultValue.innerHTML = "4개월 패키지";
if(content.style.display == 'none'){
    content.style.display = 'block';
}else{
    content.style.display = 'none';
}	
});






