// 참여한 요소와 찜한 요소를 선택합니다.
const fundingTab = document.querySelector('.list1');
const wishTab = document.querySelector('.list2');

// list2의 초기 색상을 설정합니다.
wishTab.style.color = '#90949c';

// list1을 클릭하면 실행되는 함수입니다.
function handleFundingTabClick() {
  fundingTab.style.color = '#00c4c4';
  wishTab.style.color = '#90949c';
}

// list2를 클릭하면 실행되는 함수입니다.
function handleWishTabClick() {
  fundingTab.style.color = '#90949c';
  wishTab.style.color = '#00c4c4';
}

// list1과 list2에 각각 클릭 이벤트를 추가합니다.
fundingTab.addEventListener('click', handleFundingTabClick);
wishTab.addEventListener('click', handleWishTabClick);
