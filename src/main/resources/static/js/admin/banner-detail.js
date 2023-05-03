const container = document.querySelector('.slider__container');
  
  container.addEventListener('click', (event) => {
    const target = event.target;
    if (target.tagName === 'IMG') {
      const link = target.parentElement.querySelector('a');
      const clickEvent = new MouseEvent('click');
      link.dispatchEvent(clickEvent);
    }
  });


// 배너 신청 수락, 거절
  $('#ok-button').on('click', function(){
    if($('#ok-button').text() == "수락하기"){
      $('#ok-button').text("거절하기");

    } else{
      $('#ok-button').text("수락하기");
      
    }
  });