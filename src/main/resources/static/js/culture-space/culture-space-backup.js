    /* 마우스오버 - 문화 공간 정보 노출 */
    $(function(){
        $('.culture-space-photo-container').mouseover(function(e){
           $(this).find(".photo-instance").css("opacity", "0.1"); //사진 투명도 조절
           $(this).find('.slider__arrow--right').show(); //우측 지도 버튼
           $(this).find('.slider__arrow--left').show(); //좌측 화살표
           $(this).find('.space-info').css({left: '-30%'}); //공간 정보 노출 애니메이션
           $(this).find('.space-info').animate({left:'0px'}, {duration: 700});
           $(this).find('.space-info').css("opacity", "1");
           $(this).find('.space-info').show();
        });
    });
       /* 마우스 아웃 */
        $('.culture-space-photo-container').mouseout(function(e){
         $(this).find('.photo-instance').css("opacity", "1");
         $(this).find('.slider__arrow--right').hide();
         $(this).find('.slider__arrow--left').hide();
         $(this).find('.space-info').css({left: '-20%'});
         $(this).find('.space-info').hide();
        }); 
       
      /* 이미지 배너 */
    // 슬라이더 요소와 이미지 요소를 선택
    const slider = document.querySelector('.slider');
    const container = slider.querySelector('.slider__container');
    const items = slider.querySelectorAll('.slider__item');
    
    // 왼쪽/오른쪽 화살표 요소
    const leftArrow = slider.querySelector('.slider__arrow--left');
    const rightArrow = slider.querySelector('.slider__arrow--right');
    
    // 현재 이미지의 인덱스 저장
    let currentIndex = 0;
       
    // 화살표 클릭 이벤트가 발생 시 실행될 함수
      $(document).ready(function() {
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
    function moveSlider() { //슬라이더 이동
      const itemWidth = items[currentIndex].offsetWidth; //이미지 너비 계산 
    //   currentIndex가 현재 이미지의 인덱스인데 0부터 시작해서 
    //   1을 더해줘서 현재 몇번 째 이미지인지 표시
      $('.now-page').text(currentIndex + 1);
      // 슬라이더를 이동
      container.style.transform = `translateX(-${currentIndex * itemWidth}px)`;
      
    }

});
    
    // 화살표 요소에 클릭 이벤트를 등록합니다.
    leftArrow.addEventListener('click', handleArrowClick);
    rightArrow.addEventListener('click', handleArrowClick);
    
    
    
    
    
    