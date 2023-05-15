/* mypage-profie.html */

const Calendar = tui.Calendar;
const container = document.getElementById('calendar');
const options = {
  defaultView: 'month',
  useFormPopup: true,
  useDetailPopup: true,

  gridSelection: {
    enableDblClick: false,
    enableClick: false,
  },
  month: {
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    isAlways6Weeks: false,
    visibleEventCount: 4,
  },
  timezone: {
    zones: [
      {
        timezoneName: 'Asia/Seoul',
        displayLabel: 'Seoul',
      },
      {
        timezoneName: 'Europe/London',
        displayLabel: 'London',
      },
    ],
  },
  
  
  calendars: [
    {
      id: '1',
      name: '입문',
      backgroundColor: '#2e51ef',
    },
    {
      id: '2',
      name: '실전',
      backgroundColor: '#9f8673',
    },

    {
        id: '3',
        name: '멘토링',
        backgroundColor: '#705f53',
      },

      {
        id: '4',
        name: '특강',
        backgroundColor: '#90949c',
      },
  ],
};


const calendar = new Calendar(container, options);

calendar.on('clickEvent', ({ event }) => {
  console.log(event)
});

var currentDate = calendar.getDate();


$(".year").text(currentDate.getFullYear()+ "년");
$(".month").text(currentDate.getMonth() + 1 + "월");

$("#calender-prev").click(() => {
  currentDate = calendar.getDate();

  var prevDate = new Date(currentDate.getFullYear(), currentDate.getMonth() - 1, 1);
  var prevYear = prevDate.getFullYear();
  var prevMonthIndex = prevDate.getMonth();

  $(".year").text(prevYear+ "년");
  $(".month").text(prevMonthIndex + 1 + "월");
  
  calendar.prev();
});

$("#calender-next").click(() => {
  currentDate = calendar.getDate();
  
  var nextDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 1);
  var nextMonthIndex = nextDate.getMonth();
  var nextYear = nextDate.getFullYear();

  $(".year").text(nextYear+ "년");
  $(".month").text(nextMonthIndex + 1 + "월");
  
  calendar.next();
})

$("#today").click(() => {
  $(".year").text(year+ "년");
  $(".month").text(month + "월");

  calendar.today();
});




/*  */


/* 
    달력 이벤트를 담는 법 json으로 받으면 됨
    대신 모든 정보는 string으로 받아야함
    달력에 점을 넣기 위한 코드

    *무조건 있어야함
*/
calendar.createEvents([
  {
    id: 'event1',
    calendarId: 'cal2',
    title: 'Weekly meeting',
    start: '2023-05-05T09:00:00',
    end: '2023-05-05T10:00:00',
  },
  {
    id: 'event2',
    calendarId: 'cal1',
    title: 'Lunch appointment',
    start: '2023-05-05T12:00:00',
    end: '2023-05-05T13:00:00',
  },
  {
    id: 'event3',
    calendarId: 'cal2',
    title: 'Vacation',
    start: '2022-06-08',
    end: '2022-06-10',
    isAllday: true,
    category: 'allday',
  },
]);



  /* 상세 일정을 띄워주는 코드 */
  $(document).ready(function() {

    /* document.ready를 무조건 해야 이 클래스를 로드할 수 있음 */
    $('.toastui-calendar-daygrid-cell').on('click', function() {
      let monthText = $('.month').text().trim();
      let monthMatch = monthText.match(/(\d+)월/);
      let month = monthMatch ? parseInt(monthMatch[1]) : null;
      let day = $(this).text();
      let passText = '';
      let now = new Date();
      let iconBackground = '';

      // 클릭한 날짜의 시작 시간과 끝 시간 구하기
      // start: 시작시간 00:00:00 
      // end : 끝 시간 23:59:59
      let start = new Date(Date.UTC(2023, month - 1, parseInt(day), 0, 0, 0)); // 0시 0분 0초
      let end = new Date(Date.UTC(2023, month - 1, parseInt(day), 23, 59, 59)); // 23시 59분 59초
      // 받아오는 정보
      let eventStart = '';
      let eventEnd = '';
      let eventTitle = "놀러가요"
      let eventBody = "놀러가면 재밌어요"
      let eventCategory = "농촌"
      let location = '서울시 강동구'
      let category = '박물관';

      eventStart = new Date('2023-05-04T16:00:00+09:00');
      eventEnd = new Date('2023-05-04T17:00:00+09:00')

      let kstOffset = -540; // UTC+9
      start.setMinutes(start.getMinutes() + kstOffset); // 시작 시간을 한국 시간대로 변경
      end.setMinutes(end.getMinutes() + kstOffset); // 끝 시간을 한국 시간대로 변경
      

      // start와 end를 db에 넘기고 이 사이 정보를 받아온 다음
      // 그걸 반복문 돌려서 아래의 코드를 실행
      console.log('start : ' + start)
      console.log('end : ' + end)

      /* 날짜 한국어로 변경하는 코드 */
      let startFormat = eventStart.toLocaleString('ko-KR', {
        month: 'long',
        day: 'numeric',
        hour: 'numeric',
        minute: 'numeric'
      });

      let endFormat = eventEnd.toLocaleString('ko-KR', {
        month: 'long',
        day: 'numeric',
        hour: 'numeric',
        minute: 'numeric'
      });
      /* 한국어로 변경 코드 끝 */


      if(end < now ){
        passText = '이미 지나간 체험학습 입니다'
      } else {
        passText = '현재 진행가능한 체험학습 입니다'
      }

      /* 바뀐 시간 및 이미 지나간 체험학습인지 확인 */
      console.log('passText : ' + passText)
      console.log('startFormat : ' + startFormat)
      console.log('endFormat : ' + endFormat)

      if(category == '농촌'){
        iconBackground = '#2e51ef'
      } else if(category == '스포츠'){
        iconBackground = '#9f867';
      } else if(category == '전통'){
        iconBackground = '#705f53'
      } else if(category == '박물관'){
        iconBackground = '#90949c'
      }


      /* 클릭시마다 파란색으로 바뀌기 */
      for(let i =0; i< 30;i++){
        clickedDay = $($('.toastui-calendar-daygrid-cell').children().children().children()[i])
        console.log(clickedDay.hasClass('toastui-calendar-weekday-grid-date-decorator'))
        if(clickedDay.hasClass('toastui-calendar-weekday-grid-date-decorator')){
          clickedDay.removeClass('toastui-calendar-weekday-grid-date-decorator')
          clickedDay.css('color', 'black')
        }
      }
      $(this).children().children().children().addClass('toastui-calendar-weekday-grid-date-decorator')
      $(this).children().children().children().css('color', 'white')

      let eventAll = 
      `
      <!-- 왼쪽 컨텐츠 한개 -->
      <div class="lecture">
        <div class="lecture-wrap">
        <span class="lecture-image visible">
            <img src="https://cdn.wadiz.kr/ft/images/green001/2023/0313/20230313133752303_null.jpg/wadiz/thumbnail/253/format/jpg/quality/95/) 1x, url(https://cdn.wadiz.kr/ft/images/green001/2023/0313/20230313133752303_null.jpg/wadiz/thumbnail/506/format/jpg/quality/95/" alt="">
        </span>
        
        <div class="lecture-wrapper">
          <div class="lecture-content">
            <p class="lecture-type">
              <span><i class="reward" style='background:${iconBackground}'></i>입문</span>
            </p>
            <p class="lecture-title">
              ${eventTitle}
            </p>
            <p class="lecture-subtitle">
              ${eventBody}
            </p>
            <p class="lecture-info">
              시작 : ${startFormat}
              <br> 
              종료 : ${endFormat}
              <br />장소 : ${location}
              라이브
            </p>
            <p class="lecture-number">
              <em><strong>${passText}</strong></em>
            </p>
          </div>
        </div>
      </div>
      <div class="table-wrapper">
        <table class="children-table">
          <thead>
            <tr>
              <th class="num">No.</th>
              <th class="nickname">닉네임</th>
              <th class="name">이름</th>
              <th class="place">체험 이름</th>
              <th class="participant">체험 날짜</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td class="num">1</td>
              <td class="nickname">lovelyU</td>
              <td class="name">한동석</td>
              <td class="place">진흙놀이</td>
              <td class="participant">2023-04-22 18:26:00</td>
            </tr>
            <tr>
              <td class="num">1</td>
              <td class="nickname">lovelyU</td>
              <td class="name">한동석</td>
              <td class="place">진흙놀이</td>
              <td class="participant">2023-04-22 18:26:00</td>
            </tr>
          </tbody>
        </table>
        </div>
      </div>
<!-- 한개 끝 -->
      `
      $(".lecture-list").html(eventAll)
      
    });
  

  });




      /* 달력 끝 */


      /* 해당 월을  */

      /* 처음 월 넣기 */
      $('.lecture-list-month').html($('.month').text());
      
/* 클릭시마다 월 바뀌기 */
      $('#calender-next').on('click', function(){
        $('.lecture-list-month').html($('.month').text());
      })

      $('#calender-prev').on('click', function(){
        $('.lecture-list-month').html($('.month').text());
      })


      /* 상세 페이지 누르면 table 나오게 */

$('.lecture').on('click', function(){
  let i = $(this).index();
  let tableWrapper = $('.table-wrapper').eq(i);

  if (tableWrapper.is(':visible')) { // 이미 표시되어 있는 경우
    tableWrapper.hide(); // 숨김 처리
  } else {
    $('.table-wrapper').hide(); // 모든 테이블 숨김 처리
    tableWrapper.show(); // 해당 테이블만 표시
  }
})