$(function() {
  $('.culture-space-photo-container').mouseover(function(e){
      $(this).find(".photo-instance").css("opacity", "0.1"); //사진 투명도 조절
      $(this).find('.slider__arrow--right').show(); //우측 지도 버튼
      $(this).find('.slider__arrow--left').show(); //좌측 화살표
      $(this).find('.space-info').css({left: '-30%'}); //공간 정보 노출 애니메이션
      $(this).find('.space-info').animate({left:'0px'}, {duration: 700});
      $(this).find('.space-info').css("opacity", "1");
      $(this).find('.space-info').show();
  });

  $('.culture-space-photo-container').mouseout(function(e){
      $(this).find('.photo-instance').css("opacity", "1");
      $(this).find('.slider__arrow--right').hide();
      $(this).find('.slider__arrow--left').hide();
      $(this).find('.space-info').css({left: '-20%'});
      $(this).find('.space-info').hide();
  });

  // 좌우 화살표 클릭시 슬라이드 이동
  $('.slider__arrow--left').click(function(){
      var $sliderContainer = $(this).siblings('.slider__container');
      var $sliderItem = $sliderContainer.find('.slider__item');
      var currentMarginLeft = parseInt($sliderContainer.css('margin-left'));
      var newMarginLeft = currentMarginLeft + $sliderItem.width();
      if (newMarginLeft <= 0) {
          $sliderContainer.css('margin-left', newMarginLeft + 'px');
      } else {
          $sliderContainer.css('margin-left', '0px');
      }
  });
  
  // $('.slider__arrow--right').on("click",function(){
  $(document).on("click",'slider__arrow--right',function(){
    console.log("오른쪽");
      var $sliderContainer = $(this).siblings('.slider__container');
      var $sliderItem = $sliderContainer.find('.slider__item');
      var currentMarginLeft = parseInt($sliderContainer.css('margin-left'));
      var newMarginLeft = currentMarginLeft - $sliderItem.width();
      var maxMarginLeft = -($sliderItem.width() * ($sliderItem.length - 1));
      if (newMarginLeft >= maxMarginLeft) {
          $sliderContainer.css('margin-left', newMarginLeft + 'px');
      } else {
          $sliderContainer.css('margin-left', maxMarginLeft + 'px');
      }
  });
});    
    
    
    // /* 마우스오버 - 문화 공간 정보 노출 */
    // $(function(){
    //     $('.culture-space-photo-container').mouseover(function(e){
    //       $(this).find(".photo-instance").css("opacity", "0.1"); //사진 투명도 조절
    //       $(this).find('.slider__arrow--right').show(); //우측 지도 버튼
    //       $(this).find('.slider__arrow--left').show(); //좌측 화살표
    //       $(this).find('.space-info').css({left: '-30%'}); //공간 정보 노출 애니메이션
    //        $(this).find('.space-info').animate({left:'0px'}, {duration: 700});
    //        $(this).find('.space-info').css("opacity", "1");
    //        $(this).find('.space-info').show();
    //     });
    // });
    //    /* 마우스 아웃 */
    //     $('.culture-space-photo-container').mouseout(function(e){
    //      $(this).find('.photo-instance').css("opacity", "1");
    //      $(this).find('.slider__arrow--right').hide();
    //      $(this).find('.slider__arrow--left').hide();
    //      $(this).find('.space-info').css({left: '-20%'});
    //      $(this).find('.space-info').hide();
    //     }); 



    //     $(document).ready(function() {
    //       // 슬라이더 요소와 이미지 요소를 선택
    //       const slider = $('.slider');
    //       const container = slider.find('.slider__container');
    //       const items = slider.find('.slider__item');
          
    //       // 왼쪽/오른쪽 화살표 요소
    //       const leftArrow = slider.find('.slider__arrow--left');
    //       const rightArrow = slider.find('.slider__arrow--right');
          
    //       // 현재 이미지의 인덱스 저장
    //       let currentIndex = 0;
          
    //       // 화살표 클릭 이벤트가 발생 시 실행될 함수
    //       function handleArrowClick(e) {
    //       if (e.target === leftArrow[0] && currentIndex > 0) {
    //       // 왼쪽 화살표를 클릭하여 이전 이미지로 이동합니다.
    //       currentIndex--;
    //       } else if (e.target === rightArrow[0] && currentIndex < items.length - 1) {
    //       // 오른쪽 화살표를 클릭하여 다음 이미지로 이동합니다.
    //       currentIndex++;
    //       }
    //       moveSlider();
    //       }
          
    //       function moveSlider() { //슬라이더 이동
    //       const itemWidth = items.eq(currentIndex).outerWidth(); //이미지 너비 계산
    //       // currentIndex가 현재 이미지의 인덱스인데 0부터 시작해서
    //       // 1을 더해줘서 현재 몇번 째 이미지인지 표시
    //       $('.now-page').text(currentIndex + 1);
    //       // 슬라이더를 이동
    //       container.css({transform: translateX(-${currentIndex * itemWidth}px)});
    //       }
          
    //       leftArrow.on('click', handleArrowClick);
    //       rightArrow.on('click', handleArrowClick);
    //       });



      //   var slider = $('.slider__container');
      //   var sliderItems = $('.slider__item');
      //   var sliderWidth = sliderItems.width() + parseInt(sliderItems.css('margin-right'), 10);
      //   var sliderArrowLeft = $('.slider__arrow--left');
      //   var sliderArrowRight = $('.slider__arrow--right');
      //   var currentPosition = 0;
      //   var maxPosition = sliderItems.length - 1;
      
      //   slider.css('width', sliderWidth * (maxPosition + 1) + 'px');
      //   console.log(sliderArrowRight);

      //   $(document).ready(function(){
      //     $(document).on("click", sliderArrowLeft, function(e){
      //       console.log("좌측 화살표 들어옴");
      //       if (currentPosition > 0) {
      //         currentPosition--;
      //         slider.css('transform', 'translateX(' + (-sliderWidth * currentPosition) + 'px)');
      //       }
      //     });
      //   });

      //   $(document).ready(function(){
      //   $(document).on("click", sliderArrowRight, function(e){
      //     console.log("우측 화살표 들어옴");
      //     if (currentPosition < maxPosition) {
      //       currentPosition++;
      //       slider.css('transform', 'translateX(' + (-sliderWidth * currentPosition) + 'px)');
      //     }
      //   });
      // });
        
        // $(".slider__arrow--right").on("click", function() {
        //   console.log('onclick');
        // });

  //     $('.slider__arrow--right').on("click", function(e){ 
  //       console.log('onclick들어옴');
  //       nowNum = nowNum +1;
  //       $('.slider__item ul').hide();
  //       $('.slider__item').eq(1).show();
  //       console.log($(this));
  //       console.log($(this).closest(items));
  //       if(currentIndex < $(this).closest(items).length - 1){
  //         console.log('if문 들어옴');
  //         currentIndex++;
  //       }else{
  //         currentIndex--;
  //   }
  // });

    //   /* 이미지 배너 */
    // // 슬라이더 요소와 이미지 요소를 선택
    // const slider = document.querySelector('.slider');
    // const container = document.querySelector('.slider__container');
    // const items = document.querySelectorAll('.slider__item');
    
    // // 왼쪽/오른쪽 화살표 요소
    // const leftArrow = document.querySelector('.slider__arrow--left');
    // const rightArrow = document.querySelector('.slider__arrow--right');
    
    // // 현재 이미지의 인덱스 저장
    // let currentIndex = 0;
       
    // 화살표 클릭 이벤트가 발생 시 실행될 함수
    // console.log($('.slider__arrow--right'));
    //     $('.slider__arrow--right').click(function(){
    //       console.log("클릭 들어옴");
    //       if(currentIndex > 0) {
    //         currentIndex++;
    //         console.log("if문 들어옴");
    //     }
    //   });
      


  // function moveSlider() { //슬라이더 이동
  // console.log("moveslider 들어옴");
    //   const itemWidth = $('.slider__item')[currentIndex].offsetWidth; //이미지 너비 계산 
    // //   currentIndex가 현재 이미지의 인덱스인데 0부터 시작해서 
    // //   1을 더해줘서 현재 몇번 째 이미지인지 표시
    //   $('.now-page').text(currentIndex + 1);
    //   // 슬라이더를 이동
    //   $('.slider__container').style.transform = `translateX(-${currentIndex * itemWidth}px)`;
      
   // }




//     function handleArrowClick(e) {
//       if (e.target === leftArrow && currentIndex > 0) {
//         // 왼쪽 화살표를 클릭하여 이전 이미지로 이동합니다.
//         currentIndex--;
//       } else if (e.target === rightArrow && currentIndex < items.length - 1) {
//         // 오른쪽 화살표를 클릭하여 다음 이미지로 이동합니다.
//         currentIndex++;
//       }
//       moveSlider();
//     }
//     function moveSlider() { //슬라이더 이동
//       const itemWidth = items[currentIndex].offsetWidth; //이미지 너비 계산 
//     //   currentIndex가 현재 이미지의 인덱스인데 0부터 시작해서 
//     //   1을 더해줘서 현재 몇번 째 이미지인지 표시
//       $('.now-page').text(currentIndex + 1);
//       // 슬라이더를 이동
//       container.style.transform = `translateX(-${currentIndex * itemWidth}px)`;
      
//     }

// });
    
//     // 화살표 요소에 클릭 이벤트를 등록합니다.
//     leftArrow.addEventListener('click', handleArrowClick);
//     rightArrow.addEventListener('click', handleArrowClick);
    
    
    
    
    
    