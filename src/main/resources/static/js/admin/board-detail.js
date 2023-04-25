/* 이미지 배너 */

// 슬라이더 요소와 이미지 요소를 선택합니다.
const slider = document.querySelector('.slider');
const container = slider.querySelector('.slider__container');
const items = slider.querySelectorAll('.slider__item');

// 왼쪽/오른쪽 화살표 요소를 선택합니다.
const leftArrow = slider.querySelector('.slider__arrow--left');
const rightArrow = slider.querySelector('.slider__arrow--right');

// 현재 이미지의 인덱스를 저장합니다.
let currentIndex = 0;

// 드래그 이벤트가 시작되었을 때 실행될 함수입니다.
let startClientX;
function handleDragStart(e) {
  // 드래그가 시작된 위치를 저장합니다.
  startClientX = e.clientX;
}

// // 드래그 이벤트가 종료되었을 때 실행될 함수입니다.
// function handleDragEnd(e) {
//   // 드래그가 끝난 위치와 시작 위치의 차이를 계산합니다.
//   const diff = e.clientX - startClientX;
//   if (diff > 50 && currentIndex > 0) {
//     // 오른쪽으로 드래그하여 이전 이미지로 이동합니다.
//     currentIndex--;
//   } else if (diff < -50 && currentIndex < items.length - 1) {
//     // 왼쪽으로 드래그하여 다음 이미지로 이동합니다.
//     currentIndex++;
//   }
//   moveSlider();
// }

// 화살표 클릭 이벤트가 발생했을 때 실행될 함수입니다.
function handleArrowClick(e) {
  if (e.target === leftArrow && currentIndex > 0) {
    // 왼쪽 화살표를 클릭하여 이전 이미지로 이동합니다.
    currentIndex--;
  } else if (e.target === rightArrow && currentIndex < items.length - 1) {
    // 오른쪽 화살표를 클릭하여 다음 이미지로 이동합니다.
    currentIndex++;

  }
  moveSlider();
}

// 슬라이더를 이동시키는 함수입니다.
function moveSlider() {
  // 현재 보이는 이미지의 너비를 계산합니다.
  const itemWidth = items[currentIndex].offsetWidth;
//   console.log("currentIndex : " + currentIndex);
//   currentIndex가 현재 이미지의 인덱스인데 0부터 시작해서 
//   1을 더해줘서 현재 몇번 째 이미지인지 표시
  $('.now-page').text(currentIndex + 1);

  // 슬라이더를 이동시킵니다.
  container.style.transform = `translateX(-${currentIndex * itemWidth}px)`;
}

// 각 이미지 요소에 드래그 이벤트를 등록합니다.
// items.forEach(item => {
//   item.addEventListener('dragstart', handleDragStart);
//   item.addEventListener('dragend', handleDragEnd);
// });

// 화살표 요소에 클릭 이벤트를 등록합니다.
leftArrow.addEventListener('click', handleArrowClick);
rightArrow.addEventListener('click', handleArrowClick);

/* 이미지 배너 */

/* 이미지 개수 표시 */
$(document).ready(function(){
    console.log("들어옴")
    // 이미지 개수 만큼 숫자 띄워줌
    $('.total-page').text($('.slider__item').length);
})
/* 이미지 개수 표시 */

