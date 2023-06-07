# sein-dodamdodam

<h1>청년문화 통합 플랫폼 프로젝트 - '도담도담'</h1>


<h2>1. 기획 의도</h2>
<img src="https://github.com/dev-sein/study-jpa/assets/122762143/d640309c-79ce-4883-ac88-a484ddc245dd">

현재 우리나라는 1인가구가 늘어나고 있으며, 1인가구의 핵심세대인 2030 청년들로 하여금 <strong>청년문제가 발생</strong>하고 있다는 사실에 주목하였다. 코로나나 비혼 등의 이유로 현재 청년들은 '혼자' 생활을 하는 경우가 많으며, 문화생활 역시 혼자 즐기는 경우가 많다. <strong>청년 우울감 해소와 함께 사회적 커뮤니티 형성</strong>을 위하여 도담도담에서는 <strong>청년 문화 생활 통합 플랫폼</strong>을 기획하여 청년들이 보다 더 <strong>문화공간에 쉽게 접근하고 함께하는 공동체적 가치</strong>를 만들 수 있도록 하였다.

</div>

<h2>2. 기대 효과</h2>
<img src="https://github.com/dev-sein/study-jpa/assets/122762143/213261a1-31e2-4e81-9330-a3449b83a311">

청년층 문화생활 독려를 통하여 청년들의 삶의 질, 자존감 향상들을 이끌어낸다.<br><Br>

서울시 공공데이터광장의 서울시 문화공간 정보 공공데이터를 활용하여 문화공간을 소개한다. 홈페이지 내에서 쉽게 접근하여 청년들이 보다 더 문화공간에 쉽게 접근할 수 있도록 한다.<br>
도담 모집 게시판을 통하여 청년들이 혼자여서 도전하지 못했던 취미 생활등을 즐길 수 있도록 한다. <Br><br><br>



<h2>3. 프로젝트 사용 툴</h2>
- Java<br>
- Java Spring Data<br>
- QueryDSL<br>
- Java Script<br>
- tomcat<br>
- jQuery<br>
- ORACLE<br>
- Spring Boot<br>
- Visual Studio Code<br>
- DBeaver<br>
- Sourcetree<br>
- git, github<br>
- JSON<br>
- Ajax<br>
- JDK 11.0.15<br>
- Naver DEVELOPER API<br>
- Kakao DEVELOPER API<br>
- SMTP Gmail API<br>
- Slack<br>

<h2>4. ERD</h2>
<img src="https://github.com/dev-mwYoon/dodamdodam/assets/122762471/35c5c3c8-afc6-453e-9516-248be04971f1">

 
<h2>5. 담당 업무</h2>
5-1 프론트엔드<br>
<img src="https://github.com/dev-sein/dev-sein/assets/122762143/248e4def-c7fb-4f6f-a391-d38e4fcfb608">

▶ 문화공간 정보
- 문화공간 정보 출력
- 카카오맵 API 활용 지도 출력

▶ 모집 게시판
- 모집 게시판 작성
- 모집 게시판 수정
- 모집 게시판 상세
- 모집 게시판 목록

▶ 이벤트 게시판
- 이벤트 게시판 작성
- 이벤트 게시판 수정
- 이벤트 게시판 상세
-  게시판 목록

<br>
5-2 백엔드<br>
<img src="https://github.com/dev-sein/dev-sein/assets/122762143/fe6adb96-e884-49a1-a8e8-a1baec2352fa">
<img src="https://github.com/dev-sein/dev-sein/assets/122762143/b61f98f8-b23a-46c3-bf3d-0b0983e4213b">


▶ 메인
- 헤더에 세션 삽입, 로그인 시 세션 유지
- 헤더와 메인 페이지에 각각의 게시판 링크 연결
- 각 게시판의 최신글 3개 출력과 각 항목 선택 시 상세 페이지 이동
- 사진 대표 이미지 썸네일과 글 제목, 작성일자 등 출력

▶ 고객센터
- 문의사항 폼 작성 시 문의 사항 등록
- Gmail SMTP API를 활용하여 답변 등록 

▶ 관리자 페이지 : 회원 목록
- 최신 회원 목록 10명 단위로 조회하여 페이징 처리(Restful)
- 회원 목록: 이름, 이메일, 휴대폰 번호 검색 처리(Restful)
- 회원 삭제: 삭제버튼 선택시 회원 상태 변경(Restful)
- 회원 각 항목 선택 시 모달팝업으로 회원 상세 정보 출력(Restful)
- 등급에 따른 등급 이미지 출력

▶ 관리자 페이지 : 모집 게시판
- 최신 모집게시글 목록 10 단위로 조회하여 페이징 처리(Restful)
- 모집 게시글 목록: 제목, 작성자, 모집 장소 검색 처리(Restful)
- 게시글 삭제: 모집 게시글 삭제(Restful)
- 게시글 각 항목 선택 시 상세 페이지 이동하여 출력
- 게시글 내 상세 이미지 다수일 경우 슬라이드 처리하여 출력

▶ 관리자 페이지 : 자유 게시판
- 최신 자유게시글 목록 10 단위로 조회하여 페이징 처리(Restful)
- 자유 게시글 목록: 제목, 작성자, 글 내용 검색 처리(Restful)
- 게시글 삭제: 자유 게시글 삭제(Restful)
- 게시글 각 항목 선택 시 상세 페이지 이동하여 출력
- 게시글 내 상세 이미지 다수일 경우 슬라이드 처리하여 출력

▶ 관리자 페이지 : 문의 게시판
- 최신 자유게시글 목록 10 단위로 조회하여 페이징 처리(Restful)
- 문의글 목록: 문의내용, 휴대폰 번호, 이메일 검색 처리(Restful)
- 문의글 삭제: 문의 게시글 삭제(Restful)
- 문의글 각 항목 선택 시 상세 페이지 이동하여 출력
- 문의글 답변 시 문의상태 변경
- 문의글 답변 시 SMTP Gmail을 사용하여 답변 내용 이메일 발송 처리 


<h2>6. 느낀점</h2>
<h3>6-1. 어려웠던 부분</h3>
<h4>📌 회원 등급 조회 시 참여수에 따른 비등가조인 쿼리문을 작성하는 게 어려웠다.</h4> <br>

<img src="https://github.com/dev-sein/dev-sein/assets/122762143/80b84e08-f718-4afc-a59b-75034fb4b34a"> <br>
<잘못된 코드><br>
member의 participationCount를 사용하여 grade의 gradeTitle을 조회하는 쿼리이다. 시행착오를 겪으며 어느정도 완성된 쿼리이나 계속해서 하단의 오류가 발생하였다. 그러나 내 코드에 대한 확신이 없어서 어느 부분을 수정해야할 지 찾아가는 것이 어려웠다. <br>

<img src="https://github.com/dev-sein/dev-sein/assets/122762143/c544ba9c-f1c6-4361-9948-97c3126e7ef9"> <br>
<오류 화면><br>
결과값은 memberId 마다 나와야 하기 때문에 무조건 하나의 값만 도출되어야 하는데(fetchOne) 결과값이 계속해서 여러개가 리턴되는 오류였다.<br> <br>
 
<img src="https://github.com/dev-sein/dev-sein/assets/122762143/43d76b4c-e12f-46d8-b581-1999b71fa97a"> <br>
<완성 코드><br>
 오류코드에서 where 절을 추가하면 쉽게 해결되는 코드였다. 그러나 문법에 대한 확신이 없어 헤매는 와중 마주친 오류에 원인을 찾기 어려웠다. 그래서 QueryDSL이 아닌 비등가조인 기본 예제들을 다시 한번 되짚어보며 빠진 부분을 찾고 해결했다. <br><br><br>
 
 ✔ JPA가 익숙하지 않은 상태에서 비등가조인을 작성하고자 할 때 문법 등 막막한 점이 있었다. 검색을 해보아도 <strong>QueryDSL 비등가조인</strong>에 대한 명확한 예제가 없어 직접 작성해가며 부딪히는 수밖에는 없었는데 제법 시간이 걸렸다. <strong>계속해서 주석을 달며 test를 해보는</strong> 수밖에는 없었다. 문제해결에 거의 다다랐을 때쯤에는 return 값을 계속 여러값을 불러와서 문제였는데, where 절에 memberId를 불러오지 않아 여러값을 불러왔었다. <strong>새로운 기술이라는 점을 의식하다보니, 기본적인 것을 놓친 것이다.</strong> 문제를 해결하고는 다소 허탈하긴 했으나, 구글의 도움 없이 내가 직접 새로운 기술을 적용시켰다는 점이 뿌듯했다. <br><br><br>

<h3>6-2. 문제를 해결했던 부분</h3>
<h4>📌Z-index로 해결한 카카오맵 슬라이드</h4>
🌩문제 상황🌩<br>
위도, 경도를 받아와 카카오맵 API를 사용하여 지도를 출력하는 코드에서  <br><br>
🚨문제 원인🚨 <br>
기능 구현에는 문제가 없었으나, 서버 구동 속도가 느릴 경우 callback.html 화면이 비춰지며 보이는 현상이었다. <br><br>
🚀해결 방법🚀<br>
네이버 로그인 API 설명 페이지에서는 로그인 페이지에서 데이터 이동 시 post로 callback.html로 이동시킨 후 로그인 컨트롤러로 이동할 수 있도록 설명되어 있었다. 그러나 로그인.html에서 바로 데이터를 넘겨받아 메인으로 이동하도록 하였다. 따라서 페이지 이동이 한 번 줄어들어 해당 증상이 없어졌다. 아주 간단한 생각의 전환이었지만 기다리는 사용자의 입장에서 생각해본 해결방법이었다. 
<br><br>

<h4>📌무조건 1페이지만! </h4> <br>
🌩문제 상황🌩<br>
페이징 처리를 하였으나 페이지 항목의 어떠한 숫자를 눌러도 무조건 1페이지만 출력되었다.<br><br>
🚨문제 원인🚨 <br>
<br><br>
🚀해결 방법🚀<br>
운전연수 사이트인만큼 운전면허증의 취득일자를 받는 것은 필수적이었기 때문에 해당 과정이 필요했다. 따라서 사용자가 로딩 과정을 인식할 수 있도록 처리 시작부터 마지막 순간까지 로딩중 화면을 띄워 화면에서 진행경과를 알 수 있도록 하였다. 
<br><br>

<h4>📌유지되지 않는 코스</h4> <br>
🌩문제 상황🌩<br>
첫번째 페이지에서 선택한 항목(코스)이 두번째 페이지까지 이어지지 않아 최종 연수 신청이 되지 않았다. <br><br>
🚨문제 원인🚨 <br>
운전연수 신청은 총 두페이지로 나뉜다. 페이지 이동 시 컨트롤러에서 선택값을 세션에 담았으나 페이지 이동 시 폼에서 제출되지 않았다. 
 <br>
🚀해결 방법🚀<br>
컨트롤러에서 addFlashAttribute 사용하여 값을 받아온 뒤 두번째 페이지에서 타임리프를 사용하여 input value를 받을 수 있도록 했다.


<h3>5-4. 총평</h3>
<h4>🌟 기획: 꺼진 불도 다시 보자  </h4>
개발에는 끝이 없다는 사실을 알게 되었다. 전부 완료했다고 생각하였으나, 끝이 없었다. 프론트엔드 작업 중에도 처리가 됐을 경우보다 처리가 되지 않았을 때, 올바르지 못한 경로로 접근했을 때 등을 가정하여 자바스크립트와 제이쿼리로 화면 처리를 해야했으며, 백엔드 작업에서는 세션이 담기지 않았을 때 회원만 이용할 수 있는 메뉴 등에서는 어떻게 처리해야할지, 카카오 회원가입이 되어있지 않은 상태에서 카카오 로그인을 누를 경우 등 하나하나 다 기획하여 작업해야한다는 것을 알게 되었다. 사용자가 올바른 경로로만 서비스를 이용한다고 생각하는 것이 아니라, 여러 상황을 가정하여 여러 버튼을 눌러보고 오류를 확인해야 한다는 것을 깨닫게 되었다. 


<h4>🌟 협업: 소통은 해도해도 모자르다 </h4>
팀원들과의 소통에는 자신있었다. 그러나 사이의 원만함과 빈도수와는 별개로 프로젝트 진행 시 나의 작업 상황 진행과 오류 발생 부분, 백업을 어디까지 했는지 등 상세하고 자세하게 공유해야 한다는 것을 알게 되었다. 예를 들어 프로젝트 작업 중 깃허브에서 충돌이 발생하여 브랜치 생성 시 이 내용까지 공유하지 않으면, 다른 팀원들의 코드에도 영향을 미칠 수 있으며, 이 사실을 모르는 팀원이 직접 해결하기까지는 적지 않은 시간이 소요된다는 것을 알게 되었다. 따라서 프로젝트 종료 후 팀원들과 프로젝트 리뷰 시간을 가지고 다음 프로젝트 때 부족했던 점과 보완할 점을 의견을 나누며 다음 프로젝트에서 건설적인 방향으로 나아갈 수 있도록 하였다. 


<h4>🌟 미래: 내일이 궁금한 개발자! </h4>
이번 프로젝트에서는 막연히 상상만 하던 api를 직접 구현해보았다. 특히나, 글자를 인식하는 구글 클라우드 비전 api를 사용해본 것이 매우 신기하고 재밌었다. 물론 시작은 어려웠지만, 주변의 도움과 혼자만의 코드 연구를 통하여 기능을 구현할 수 있었다. 하나하나 완성될 때마다 뿌듯함을 느낄 수 있었다. 이후 다음 프로젝트를 기획하면서 내가 구현했던 기능 뿐만 아니라 다른 api도 사용해보고 싶다는 갈증이 생겼다. 예를 들어 게시글 네이버 공유하기 api를 진행해보고 싶다. 






