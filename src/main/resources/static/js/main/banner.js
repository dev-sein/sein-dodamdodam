HTMLCollection.prototype.forEach = Array.prototype.forEach;
const banners = document.querySelector("div.main_banner_all"); // 배너들을 감싼 총 div
const imageDiv = document.querySelectorAll("div.main_banner_all div.main_banner"); // 각각의 이미지가 담기는 배너 리스트
// const lastImageDiv = document.createElement("div"); // 마지막 이미지를 담을 div 생성
// const firstImageDiv = document.createElement("div"); // 첫번째 이미지를 담을 div 생성
const next = document.querySelector(".main_banner_left_button"); // 다음 버튼
const prev = document.querySelector(".main_banner_right_button"); // 이전 버튼
const buttons = document.querySelectorAll(".main_banner_button button"); // 버튼 2개 모두의 리스트
const $bannerActive = $(".main_banner_current_bar"); // 움직이는 배너 바
const bannerPercentage = (100 / (imageDiv.length - 2)); // 배너 바 매번 증가해야하는 수치
// const lastImageDiv = document.querySelector("div.main_banner_all div.main_banner[0]");
// const firstImageDiv = document.querySelector("div.main_banner_all div.main_banner[parseInt(imageDiv.length)]");
const $mainColor = $(".banner_container");
const $currentPage = $('.current_page');


let checkArrow = false;
let count = 1; // 초기 값 배너 순서?
let auto = setInterval(autoSlide, 2000); // 2초마다 자동 슬라이드
let temp = buttons[0]; // 현재 버튼은 display none 상태

HTMLCollection.prototype.forEach = Array.prototype.forEach;
HTMLCollection.prototype.map = Array.prototype.map;



buttons.forEach(button => {
    button.addEventListener("click", () => {
        clearInterval(auto);
        count = parseInt(button.innerHTML);
        changeButtonStyle();
        banners.style.transition = "transform 0.3s";
        banners.style.transform = `translate(${-580 * count}px)`;
        auto = setInterval(autoSlide, 2000);
    });
});
// imageDiv.forEach((div, i) => div.style.backgroundImage = `url(/images/main/00${i+1}.jpg)`); // 배너 리스트 안에 각 배너들에 이미지를 style을 줌.
imageDiv.forEach((div, i) => div.style.backgroundImage = `url(/static/images/main/00${i+1}.jpg)`); // 배너 리스트 안에 각 배너들에 이미지를 style을 줌.
// imageDiv[0].style.backgroundImage = `url(/images/main/00${imageDiv.length}.jpg)`;
// imageDiv[parseInt(${imageDiv.length})].style.backgroundImage = `url(/images/main/00${imageDiv.length}.jpg)`;

imageDiv.forEach((div, i) => console.log(div, i));

// banners.appendChild(lastImageDiv); // 총 배너의 자식인 배너 div(lastImageDiv) 하나 추가
// lastImageDiv.style.backgroundImage = `url(/images/main/001.jpg)`;


// banners.insertBefore(firstImageDiv, document.querySelector("div.banners div.banner"));
// firstImageDiv.style.backgroundImage = `url(/images/main/00${imageDiv.length}.jpg)`
// console.log(firstImageDiv);
// console.log(lastImageDiv);

banners.style.transform = `translate(-580px)`;


function changeButtonStyle() {
    // if(temp){
    //     temp.style.background = "white";
    //     temp.style.color = "black";
    // }
    // buttons[count - 1].style.background = "black";
    // buttons[count - 1].style.color = "white";
    temp = buttons[count - 1];
    $bannerActive.css("width", `${bannerPercentage * count}%`); /* 배너 바 현재 퍼센트 */
    $mainColor.css("background-color", `var(--banner${count}-color)`); // 메인 컬러 변경
    $currentPage.text(count);
}

function autoSlide(){
    banners.style.transition = "transform 0.3s"; // 0.3초동안 이동하는 애니메이션 효과
    banners.style.transform = `translate(${-580 * ++count}px)`;
    // console.log(count);

    if(count == 4) { /* 6->7로 넘어가게 되면*/
        count = 1; /* count는 다시 1 */
        setTimeout(function(){ 
            banners.style.transition = "transform 0s"; /* 같은 이미지 이동 시 애니메이션 끔. */
            banners.style.transform = `translate(-580px)`; /* 이동 하는 배너 위치 */
        }, 300);
    }
    
    changeButtonStyle();
    
}

/* ==================================== */

prev.addEventListener("click", function(){
    if(checkArrow){return;}
    checkArrow = true;
    clearInterval(auto);
    banners.style.transition = "transform 0.3s";
    banners.style.transform = `translate(${-580 * --count}px)`;
    if(count == 0) {
        count = 3;
        setTimeout(function(){
            banners.style.transition = "transform 0s";
            banners.style.transform = `translate(${-580 * (imageDiv.length)}px)`;
        }, 300);
    }
    changeButtonStyle();
    auto = setInterval(autoSlide, 2000);
    setTimeout(()=>{checkArrow = false}, 300);
});

next.addEventListener("click", function(){
    if(checkArrow){return;}
    checkArrow = true;
    clearInterval(auto);
    banners.style.transition = "transform 0.3s";
    banners.style.transform = `translate(${-580 * ++count}px)`;
    if(count == 4) {
        count = 1;
        setTimeout(function(){
            banners.style.transition = "transform 0s";
            banners.style.transform = `translate(-580px)`;
        }, 300);
    }
    changeButtonStyle();
    auto = setInterval(autoSlide, 2000);
    setTimeout(()=>{checkArrow = false}, 300);
});



