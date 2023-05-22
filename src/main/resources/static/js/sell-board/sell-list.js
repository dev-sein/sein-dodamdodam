// $(document).ready(function() {
//     // 검색창에서 키보드를 눌렀을 때
//     $('.search__searchbox__form').on('keydown', function(e) {
//         if (e.keyCode == 13) { // Enter 키를 눌렀을 때
//             e.preventDefault(); // 기본 이벤트 막기
//         }
//     });
// });


let page = 1;
let keyword;
load();

function load() {

    console.log(page);
    $.ajax({
        url: "/boards/puchase/list",
        type: "post",
        contentType: 'application/json',
        dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
        data : JSON.stringify({  // 보낼 데이터 (Object , String, Array)
            "purchaseBoardSearch" : purchaseBoardSearch,
            "pageable" : pageable
        }),
        success: function(result) {
            // console.log(page);
            // console.log(keyword);
            // console.log(result.pagination.realEnd);
            // console.log(result.pagination.endPage);
            // console.log(result.pagination.prev);
            // console.log(result.pagination.next);
            showList(result.members);
            showPage(result.pagination);

        },
        error: function (error) {
            console.log('Error fetching data:', error);
        }
    })
};
/* 핸드폰 자동 하이픈 */
function autoHyphen(number) {
    const regex = /^01(0|1|[6-9])\-?\d{3,4}\-?\d{4}$/;
    if (regex.test(number)) {
        return number.replace(/^01(0|1|[6-9])/, "01$1-").replace(/(\d{3,4})(\d{4})/, "$1-$2");
    }
    return "Invalid number";
}
/*신청 목록*/
function showList(members){
    const $listResults = $("#scroll");
    var text = "";
    members.forEach(member => {
        var date = member.memberDriveRegisterDate;
        var memberType = member.memberType == 0 ? "초보자" : "베테랑";
        var memberPhone = autoHyphen(member.memberPhone);
        var realDate = changeDate(date);
        text +=`
            <div class="user-list__info-container">
                <div class="user-list__info-unit">
                    <input type="checkbox" class="user__checkbox" id="" name="checkbox" data-id="${member.memberId}" onclick="isChecked(this)"/>
                    <label for="" class="user__checkbox--label">
                        <ul class="user-list__info">
                            <li class="user__id" name="memberId">${member.memberId}</li>
							<li class="user__type" name="memberType">${memberType}</li>
							<li class="user__user-id" name="memberIdentification">${member.memberIdentification}</li>
							<li class="user__name" name="memberName">${member.memberName}</li>
							<li class="user__email" name="memberEmail">${member.memberEmail}</li>
							<li class="user__phone" name="memberPhone">${memberPhone}</li>
							<li class="user__point" name="memberPoint">${member.memberPoint}</li>
							<li class="user__join" name="memberDriveRegisterDate">${realDate}</li>
                            <li class="user__detail" name="userDetail">
                                <button class="custom-btn btn-16 show" data-id="${member.memberId}" id="show" onclick="show(this)">상세 정보</button>
                            </li>
                        </ul>
                    </label>
                </div>
            </div>
        `
    });

    $listResults.html(text);
}


/*페이지 버튼*/
function showPage(pagination){
    const $btnResults = $(".desktop-only");
    page = pagination.criteria.page;
    var text = `
            <button class="prev-page icon-chevron-left" data-page="${pagination.startPage - 1}" onclick="findPage(this)" ${pagination.prev ? '' : 'disabled'}>
                <span class="text-hidden">이전</span>
            </button>`;
    for (let i = pagination.startPage; i <= pagination.endPage; i++) {
        text += `<a class="pages ${pagination.criteria.page === i ? 'current' : ''}" data-page="${i}" onclick="findPage(this)">${i}</a>`;
    }
    text += `
            <button class="next-page icon-chevron-right" data-page="${pagination.endPage + 1}" onclick="findPage(this)" ${pagination.next ? '' : 'disabled'}>
                <span class="text-hidden">다음</span>
            </button>`;



    $btnResults.html(text);
}

function findPage(currentPage) {
    page = currentPage.dataset.page;
    page *= 1;
    // console.log(typeof page);
    load();
}


$('.search__searchbox__button').on('click', showKeyword)
$('#searchbox').on('keyup', showKeyword)

function showKeyword() {
    keyword = $('#searchbox').val();
    page = 1
    console.log(keyword);
    load();
}







