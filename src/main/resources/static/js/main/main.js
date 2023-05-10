const frequentSelect = document.querySelector('.guide-frequent-select');
const useSelect = document.querySelector('.guide-use-select');
const reserveSelect = document.querySelector('.guide-reserve-select');
const use = document.querySelector('.guide-frequent');
const frequent = document.querySelector('.guide-use');
const reserve = document.querySelector('.guide-reserve');


frequentSelect.style.width = '';
useSelect.style.width = '';
reserveSelect.style.width = '';


// /* background-color: rgb(0, 196, 196); */
// background-color: var(--select-color);

// /* background-color:  rgb(0, 178, 178); */
// background-color:  var(--main-sub3);

let currentSelection = null; // 현재 선택된 영역을 추적하기 위한 변수

/* frequentSelect를 마우스 오버시 다른 요소 바꾸기 */
if (frequentSelect) {
  frequentSelect.addEventListener('mouseover', function() {
    if (currentSelection !== 'frequent') {
      currentSelection = 'frequent';
      
      frequentSelect.style.width = '378px';
      frequentSelect.style.backgroundColor = 'var(--select-color)';
      useSelect.style.width = '104px';
      useSelect.style.backgroundColor = 'var(--no-select-color)';
      reserveSelect.style.width = '104px';
      reserveSelect.style.backgroundColor = 'var(--no-select-color)';

      $(".guide-frequent-container").css("display", "flex");
      $(".guide-use-container").css("display", "none");
      $(".guide-select-container").css("display", "none");
      $(".guide-frequent").css("display", "none");
      $(".guide-use").css("display", "block");
      $(".guide-reserve").css("display", "block");
    }
  });

}

/* useSelect를 마우스 오버시 다른 요소 바꾸기 */
if (useSelect) {
  useSelect.addEventListener('mouseover', function() { 
    if (currentSelection !== 'use') {
      currentSelection = 'use';
      
      useSelect.style.width = '378px';
      useSelect.style.backgroundColor = 'var(--select-color)';
      frequentSelect.style.width = '104px';
      frequentSelect.style.backgroundColor = 'var(--no-select-color)';
      reserveSelect.style.width = '104px';
      reserveSelect.style.backgroundColor = 'var(--no-select-color)';

      $(".guide-use-container").css("display", "flex");
      $(".guide-frequent-container").css("display", "none");
      $(".guide-reserve-container").css("display", "none");
      $(".guide-use").css("display", "none");
      $(".guide-frequent").css("display", "block");
      $(".guide-reserve").css("display", "block");
    }
  });

}

/* reserveSelect를 마우스 오버시 다른 요소 바꾸기 */
if (reserveSelect) {
  reserveSelect.addEventListener('mouseover', function() {
    if (currentSelection !== 'reserve') {
      currentSelection = 'reserve';

      reserveSelect.style.width = '378px';
      reserveSelect.style.backgroundColor = 'var(--select-color)';
      frequentSelect.style.width = '104px';
      frequentSelect.style.backgroundColor = 'var(--no-select-color)';
      useSelect.style.width = '104px';
      useSelect.style.backgroundColor = 'var(--no-select-color)';
      
      $(".guide-reserve-container").css("display", "flex");
      $(".guide-frequent-container").css("display", "none");
      $(".guide-use-container").css("display", "none");
      $(".guide-reserve").css("display", "none");
      $(".guide-frequent").css("display", "block");
      $(".guide-use").css("display", "block");
    }
  });

}

$('.recruit-swiper-inner-container').each((i, e) => {
    $(e).mouseover(() => {
       $($(".recruit-box-context")[i]).css("transform", "translate(0, -180px)");
       $($(".recruit-box-context")[i]).css("background", "linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.4))");
    });
    $(e).mouseout(() => {
       $($(".recruit-box-context")[i]).css("transform", "translate(0, 0px)");
       $($(".recruit-box-context")[i]).css("background", "linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0))");
    });
});

// 


HTMLCollection.prototype.forEach = Array.prototype.forEach;
const eventBanner = document.querySelector('div.event-banner');
const eventImageDiv = document.querySelectorAll('div.event-banner div .event-banner-image');
const eventFirstImageDiv = document.createElement('div');
const eventNext = document.querySelector('div.event-next');
const eventPrev = document.querySelector('div.event-prev');
const eventButtons = document.querySelectorAll('.event-buttons button');
