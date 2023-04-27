const fundingTab = document.querySelector('.list1');
const wishTab = document.querySelector('.list2');
const boardTab = document.querySelector('.list3');

const projectList1 = document.querySelector('.project-list1');
const projectList2 = document.querySelector('.project-list2');
const projectList3 = document.querySelector('.project-list3');

function hideAllLists() {
  projectList1.style.display = 'none';
  projectList2.style.display = 'none';
  projectList3.style.display = 'none';
}

// 색상 변경 함수 추가
function resetTabColors() {
  fundingTab.style.color = '#90949c';
  wishTab.style.color = '#90949c';
  boardTab.style.color = '#90949c';
}

function handleFundingTabClick() {
  hideAllLists();
  resetTabColors();
  projectList1.style.display = 'block';
  fundingTab.style.color = '#00c4c4';
}

function handleWishTabClick() {
  hideAllLists();
  resetTabColors();
  projectList2.style.display = 'block';
  wishTab.style.color = '#00c4c4';
}

function handleBoardTabClick() {
  hideAllLists();
  resetTabColors();
  projectList3.style.display = 'block';
  boardTab.style.color = '#00c4c4';
}

fundingTab.addEventListener('click', handleFundingTabClick);
wishTab.addEventListener('click', handleWishTabClick);
boardTab.addEventListener('click', handleBoardTabClick);

hideAllLists();
projectList1.style.display = 'block';
fundingTab.style.color = '#00c4c4';
wishTab.style.color='#90949c';
boardTab.style.color='#90949c';
