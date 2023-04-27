/* -------- 캘린더 시작 ----------*/
document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');
  var calendar = new FullCalendar.Calendar(calendarEl, {

initialView: 'dayGridMonth',
selectable: true,// 달력 일자 드래그 설정가능
navLinks: true, // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크
editable: true, // 수정 가능
nowIndicator: true, // 현재 시간 마크
dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
locale: 'ko', // 한국어 설정
eventAdd: function(obj) { // 이벤트가 추가되면 발생하는 이벤트
  console.log('add');
  
},
eventChange: function(obj) { // 이벤트가 수정되면 발생하는 이벤트
  
  // GMT 시간은 9시간 빨라서 9시간 빼줘야됨
  var start = obj.event._instance.range.start;
  if(start.getHours() == 9) {
    start = moment(start).format('YYYY-MM-DD') + " 00:00";
  }
  else {
    start = start.setHours(start.getHours() - 9);
    start = moment(start).format('YYYY-MM-DD hh:mm');
  }
  
  
  var end = obj.event._instance.range.end;
  if(end.getHours() == 9) {
    end = moment(end).format('YYYY-MM-DD') + " 00:00";
  }
  else {
    end = end.setHours(end.getHours() - 9);
    end = moment(end).format('YYYY-MM-DD hh:mm');
  }
  
  
  $.ajax({
        url: "/~team2/admin/ajax_calendar_edit",
        type: "POST",
        data : {
          title : obj.event._def.title,
          start: start,
          end: end
        },
        traditional: true,
        async: false, //동기
        success : function(data){
          
        },
        error : function(request,status,error){
        alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
        console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error);
        }
    });
  
},
eventRemove: function(obj){ // 이벤트가 삭제되면 발생하는 이벤트
  console.log('remove');
  
},
select: function(arg) { // 캘린더에서 드래그로 이벤트 생성
  
  var title = prompt('입력할 일정:');
  if (title) {
    $.ajax({
        url: "/~team2/admin/ajax_calendar_add",
        type: "POST",
        data : {
          title: title,
          start: arg.startStr,
          end: arg.endStr,
          allDay: arg.allDay
        },
        traditional: true,
        async: false, //동기
        success : function(data){
          
        },
        error : function(request,status,error){
        alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
        console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error);
        }
    });

    calendar.addEvent({
      title: title,
      start: arg.startStr,
      end: arg.endStr,
      allDay: arg.allDay
    })
  } 
  
  calendar.unselect()
},
droppable: true,
eventClick: function(arg) { 
  // 있는 일정 클릭시, 
  console.log(arg);
  if (confirm('일정을 삭제하시겠습니까?')) 
  { 
    $.ajax({
        url: "/~team2/admin/ajax_calendar_delete",
        type: "POST",
        data : {
          title : arg.event._def.title
        },
        traditional: true,
        async: false, //동기
        success : function(data){
         
        },
        error : function(request,status,error){
        alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
        console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error);
        }
    });
    arg.event.remove();
  } 
},
eventBorderColor : '#82d1ff', // 이벤트 테두리색
eventBackgroundColor : '#82d1ff' , // 이벤트 배경색
headerToolbar: { // 툴바
  left: 'prev,next today',
  center: 'title',
  right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
},
events: function(info, successCallback, failureCallback){ // ajax 처리로 데이터를 로딩 시킨다. 
  $.ajax({
      url: "/~team2/admin/ajax_calendar_load",
      type: "POST",
      dataType: "JSON",
      traditional: true,
      async: false, //동기
      success : function(data){
        
      successCallback(data);
      
      },
      error : function(request,status,error){
      alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
      console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error);
      }
  });
}

  });

  calendar.render(); // 캘린더 렌더링(화면 출력)
});

/* -------- 캘린더 끝 ----------*/