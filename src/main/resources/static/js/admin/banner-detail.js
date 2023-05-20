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


/*항목 삭제*/
$(document).ready(function() {
  // 삭제 버튼 클릭 시
  $('#delete-button').click(function() {
    var selectedItems = [];
    // 체크된 항목의 ID를 배열에 추가
    $('input.substituted.select-member:checked').each(function() {
      var bannerId = $(this).closest('tr').find('.numbers').text();
      selectedItems.push(parseInt(bannerId));
    });

    // 선택된 항목이 없는 경우 경고창을 표시하고 함수를 종료
    if (selectedItems.length === 0) {
      alert('삭제할 항목을 선택해주세요.');
      return;
    }
    $('#delete-modal').show(); //삭제 모달창 열기
    $('#confirm-btn').click(function() {//모달창의 확인 버튼 눌렀을 경우 데이터 삭제
      $.ajax({
        url: '/admins/banner/delete',
        type: 'DELETE',
        contentType: 'application/json',
        data: JSON.stringify(selectedItems),
        success: function (response) {
          // alert(response); // 서버로부터의 응답 메시지를 알림으로 표시(모달로 바꾸기)
          location.reload() //삭제완료 후 새로고침
        },
        error: function (xhr, status, error) {
          alert('오류가 발생했습니다. 다시 시도해주세요.');
          console.log(error);
        }
      });
    });
  });
});



/*항목 수정*/
$(document).ready(function() {
  // 삭제 버튼 클릭 시
  $('#ok-button').click(function() {
    var selectedItems = [];
    // 체크된 항목의 ID를 배열에 추가
    $('input.substituted.select-member:checked').each(function() {
      var inquiryId = $(this).closest('tr').find('.numbers').text();
      selectedItems.push(parseInt(inquiryId));
    });

    // 선택된 항목이 없는 경우 경고창을 표시하고 함수를 종료
    if (selectedItems.length === 0) {
      alert('삭제할 항목을 선택해주세요.');
      return;
    }
    $('#delete-modal').show(); //삭제 모달창 열기
    $('#confirm-btn').click(function() {//모달창의 확인 버튼 눌렀을 경우 데이터 삭제
      $.ajax({
        url: '/admins/banner/modify',
        type: 'PATCH',
        contentType: 'application/json',
        data: JSON.stringify(selectedItems),
        success: function (response) {
          // alert(response); // 서버로부터의 응답 메시지를 알림으로 표시(모달로 바꾸기)
          location.reload() //삭제완료 후 새로고침
        },
        error: function (xhr, status, error) {
          alert('오류가 발생했습니다. 다시 시도해주세요.');
          console.log(error);
        }
      });
    });
  });
});