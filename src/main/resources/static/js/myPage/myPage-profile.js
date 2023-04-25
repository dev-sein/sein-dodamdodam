// 참여한 요소, 찜한 요소, 게시판 요소를 선택합니다.
const fundingTab = document.querySelector('.list1');
const wishTab = document.querySelector('.list2');
const boardTab = document.querySelector('.list3');

// list2와 list3의 초기 색상을 설정합니다.
wishTab.style.color = '#90949c';
boardTab.style.color = '#90949c';

// list1을 클릭하면 실행되는 함수입니다.
function handleFundingTabClick() {
  fundingTab.style.color = '#00c4c4';
  wishTab.style.color = '#90949c';
  boardTab.style.color = '#90949c';
}

// list2를 클릭하면 실행되는 함수입니다.
function handleWishTabClick() {
  fundingTab.style.color = '#90949c';
  wishTab.style.color = '#00c4c4';
  boardTab.style.color = '#90949c';
}

// list3를 클릭하면 실행되는 함수입니다.
function handleBoardTabClick() {
  fundingTab.style.color = '#90949c';
  wishTab.style.color = '#90949c';
  boardTab.style.color = '#00c4c4';
}

// list1, list2, list3에 각각 클릭 이벤트를 추가합니다.
fundingTab.addEventListener('click', handleFundingTabClick);
wishTab.addEventListener('click', handleWishTabClick);
boardTab.addEventListener('click', handleBoardTabClick);


/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ *//* 
// 참여한 요소, 찜한 요소, 게시판 요소를 선택합니다.
const fundingTab = document.querySelector('.list1');
const wishTab = document.querySelector('.list2');
const boardTab = document.querySelector('.list3');

// 프로젝트 리스트1, 프로젝트 리스트2를 선택합니다.
const projectList1 = document.querySelector('.project-list1');
const projectList2 = document.querySelector('.project-list2');
const projectList3 = documnet.querySelector('.project-list3')

// list2와 list3의 초기 색상을 설정합니다.
wishTab.style.color = '#90949c';
boardTab.style.color = '#90949c';


// 프로젝트 리스트1은 보이게 하고, 프로젝트 리스트2는 숨깁니다.
projectList1.style.display = 'block';
projectList2.style.display = 'none';
projectList3.style.display ='none' ;

// list1을 클릭하면 실행되는 함수입니다.
function handleFundingTabClick() {
    fundingTab.style.color = '#00c4c4';
    wishTab.style.color = '#90949c';
    boardTab.style.color = '#90949c';

    // 프로젝트 리스트1은 보이게 하고, 프로젝트 리스트2는 숨깁니다.
    projectList1.style.display = 'block';
    projectList2.style.display = 'none';
    projectList3.style.display ='none' ;
}

// list2를 클릭하면 실행되는 함수입니다.
function handleWishTabClick() {
    fundingTab.style.color = '#90949c';
    wishTab.style.color = '#00c4c4';
    boardTab.style.color = '#90949c';

    // 프로젝트 리스트1은 숨기고, 프로젝트 리스트2는 보이게 합니다.
    projectList1.style.display = 'none';
    projectList2.style.display = 'block';
    projectList3.style.display ='none' ;
}

// list3를 클릭하면 실행되는 함수입니다.
function handleBoardTabClick() {
  fundingTab.style.color = '#90949c';
  wishTab.style.color = '#90949c';
  boardTab.style.color = '#00c4c4';

   // 프로젝트 리스트1은 숨기고, 프로젝트 리스트2는 보이게 합니다.
   projectList1.style.display = 'none';
   projectList2.style.display = 'none';
   projectList3.style.display ='block' ;
}


// list1과 list2에 각각 클릭 이벤트를 추가합니다.
fundingTab.addEventListener('click', handleFundingTabClick);
wishTab.addEventListener('click', handleWishTabClick);
boardTab.addEventListener('click', handleBoardTabClick); */