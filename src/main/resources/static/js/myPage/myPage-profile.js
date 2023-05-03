const writeTab = document.querySelector('.list1');
const collectTab = document.querySelector('.list2');
const sellTab = document.querySelector('.list3');
const joinTab = document.querySelector('.list4');
const eventTab = document.querySelector('.list5');

const projectList1 = document.querySelector('.project-list1');
const projectList2 = document.querySelector('.project-list2');
const projectList3 = document.querySelector('.project-list3');
const projectList4 = document.querySelector('.project-list4');
const projectList5 = document.querySelector('.project-list5');

function hideAllLists() {
  projectList1.style.display = 'none';
  projectList2.style.display = 'none';
  projectList3.style.display = 'none';
  projectList4.style.display = 'none';
  projectList5.style.display = 'none';
}

function resetTabColors() {
  writeTab.style.color = '#90949c';
  collectTab.style.color = '#90949c';
  sellTab.style.color = '#90949c';
  joinTab.style.color = '#90949c';
  eventTab.style.color = '#90949c';
}

function handlewriteTabClick() {
  hideAllLists();
  resetTabColors();
  projectList1.style.display = 'block';
  writeTab.style.color = '#006633;';
}

function handlecollectTabClick() {
  hideAllLists();
  resetTabColors();
  projectList2.style.display = 'block';
  collectTab.style.color = '#006633;';
}

function handlesellTabClick() {
  hideAllLists();
  resetTabColors();
  projectList3.style.display = 'block';
  sellTab.style.color = '#006633;';
}

function handlejoinTabClick() {
  hideAllLists();
  resetTabColors();
  projectList4.style.display = 'block';
  joinTab.style.color = '#006633;';
}

function handlejoinTabClick() {
  hideAllLists();
  resetTabColors();
  projectList4.style.display = 'block';
  eventTab.style.color = '#006633;';
}

writeTab.addEventListener('click', handlewriteTabClick);
collectTab.addEventListener('click', handlecollectTabClick);
sellTab.addEventListener('click', handlesellTabClick);
joinTab.addEventListener('click', handlejoinTabClick);
eventTab.addEventListener('click', handlejoinTabClick);


hideAllLists();
projectList1.style.display = 'block';
writeTab.style.color = '#006633;';
collectTab.style.color='#90949c';
sellTab.style.color='#90949c';
joinTab.style.color='#90949c';
eventTab.style.color='#90949c';


// 각 리스트 클릭 시 색상 변경 함수 추가
writeTab.addEventListener('click', function() {
  writeTab.style.color = '#006633';
  collectTab.style.color = '#90949c';
  sellTab.style.color = '#90949c';
  joinTab.style.color = '#90949c';
  eventTab.style.color = '#90949c';
});

collectTab.addEventListener('click', function() {
  writeTab.style.color = '#90949c';
  collectTab.style.color = '#006633';
  sellTab.style.color = '#90949c';
  joinTab.style.color = '#90949c';
  eventTab.style.color = '#90949c';
});

sellTab.addEventListener('click', function() {
  writeTab.style.color = '#90949c';
  collectTab.style.color = '#90949c';
  sellTab.style.color = '#006633';
  joinTab.style.color = '#90949c';
  eventTab.style.color = '#90949c';
});

joinTab.addEventListener('click', function() {
  writeTab.style.color = '#90949c';
  collectTab.style.color = '#90949c';
  sellTab.style.color = '#90949c';
  joinTab.style.color = '#006633';
  eventTab.style.color = '#90949c';
});

eventTab.addEventListener('click', function() {
  writeTab.style.color = '#90949c';
  collectTab.style.color = '#90949c';
  sellTab.style.color = '#90949c';
  joinTab.style.color = '#90949c';
  eventTab.style.color = '#006633';
});


