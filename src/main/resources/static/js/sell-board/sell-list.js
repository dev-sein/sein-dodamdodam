globalThis.page = 1;
let purchaseBoardSearch = {
    boardTitle : null,
    memberName : null
};
let keyword;
const $listResults = $(".event-content");

load(page, purchaseBoardSearch); // 시작하자마자 실행

function keyDownEnter() {
    // 검색창에서 키보드를 눌렀을 때
    $('.input-keyword').on('keydown', function(e) {
        if (e.keyCode == 13) { // Enter 키를 눌렀을 때
            e.preventDefault(); // 기본 이벤트 막기
            keyDownEnterOrClickSearchButtonAfter();
        }
    });
}

function keyDownEnterOrClickSearchButtonAfter() {
    $listResults.empty(); // 기존 내용 비우기
    page = 1;
    showKeyword(); // purchaseBoardSearch에 키워드 담아주는 메소드
    load(page, purchaseBoardSearch);
}


window.addEventListener('scroll', function() {
    // 현재 스크롤 위치 확인
    var scrollPosition = window.pageYOffset;
    var windowSize = window.innerHeight;
    var bodyHeight = document.body.offsetHeight;

    page ++;

    // 스크롤이 페이지 맨 아래에 도달할 때 데이터 로드
    if (scrollPosition + windowSize >= bodyHeight) {
        load(page, purchaseBoardSearch);
    }
});

function changeDate(e) {
    var date = new Date(e);
    var year = date.getFullYear();
    var month = ("0" + (1 + date.getMonth())).slice(-2);
    var day = ("0" + date.getDate()).slice(-2);

    return year + "." + month + "." + day;
}

function calculateDate(inputDate) {
    // 현재 날짜를 가져옵니다.
    var currentDate = new Date();
    // 입력 받은 날짜를 파싱합니다.
    var parsedInputDate = new Date(inputDate);
    // 리턴 결과값
    let result = "";

    if (currentDate >= parsedInputDate) {
        // 현재 날짜가 입력 받은 날짜보다 같거나 지났을 때
        result = "진행중";

    } else/* (currentDate < parsedInputDate) */{
        // 현재 날짜가 아직 입력 받은 날짜가 되지 않았을 때
        result = "진행예정"
    }

    return result;
}

function load(page, purchaseBoardSearch) {

    console.log(purchaseBoardSearch);
    console.log(page);

    $.ajax({
        url: `/purchase/list-content/${globalThis.page}`,
        type: "get",
        data : purchaseBoardSearch,
        success: function(purchaseBoardDTOs) {
            console.log("ajax success");
            showList(purchaseBoardDTOs);
        },
        error: function (error) {
            console.log('Error fetching data:', error);
        }
    })
};

/*목록*/
function showList(list){
    let purchaseBoardDTOs = list.content;

    var text = "";
    if (list != null || $listResults.hasChildNodes()) {
        purchaseBoardDTOs.forEach(purchaseBoardsDTO => {
            console.log(purchaseBoardsDTO);
            var date = purchaseBoardsDTO.createdDate;
            var realDate = changeDate(date);
            let presentFileDTO = purchaseBoardsDTO.purchaseFileDTOs[0];
            let filePath = '/file/display?fileName=' + presentFileDTO.filePath + '/t_' + presentFileDTO.fileUuid + '_' + presentFileDTO.fileOriginalName;
            text +=`
            <li class="event-instance" >
                <div class="instance">
                    <img class="thumbnail" src="${filePath}" onclick="location.href='/purchase/detail/${purchaseBoardsDTO.id}'">
                    <div class="instance-detail">
    
                        <div class="detail-title">${purchaseBoardsDTO.boardTitle}</div>
                        <div class="detail-writer">${purchaseBoardsDTO.memberDTO.memberName}</div>
                        <div class="detail-date">${realDate}</div>
                    </div>
                </div>
            </li>
        `
        });
    } else {
        text += `
            <li class="event-instance" >
                <div class="instance">
                    <div class="instance-detail">
                        <div class="detail-title">검색 결과가 없습니다.</div>
                    </div>
                </div>
            </li>
        `;
    }

    $listResults.append(text);
}


$('.search-button').on('click', keyDownEnterOrClickSearchButtonAfter);
// $('#searchbox').on('keyup', showKeyword)

function showKeyword() {
    let $selectedVal = $(".total-inner select").val();
    if($selectedVal === "boardTitle") {
        purchaseBoardSearch.boardTitle = $('.search-input').val();
        console.log("showKeyworkd() if문 들어옴");
    } else {
        purchaseBoardSearch.memberName = $('.search-input').val();
        console.log("showKeyworkd() else문 들어옴");
    }
    console.log(purchaseBoardSearch);
}







