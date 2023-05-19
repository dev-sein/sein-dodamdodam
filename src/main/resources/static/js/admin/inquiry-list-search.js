const $listResults = $("#inquiryTable tbody");
const PAGE_AMOUNT = 10;
const SEARCH_URL = "/parentsYard/list/show";
const $pageWrap = $(".paging-list");
const inquirySearch = {
    inquiryType: null,
    inquiryEmail: null,
    inquiryPhoneNumber: null,
    inquiryStatus: null
};


// 카테고리 별 정렬

function getInquiryList() {
    console.log("ajax 들어옴");
    $.ajax({
        url: `/admins/inquiry/list/${globalThis.page}`,
        data: inquirySearch,
        success: function (data) {
            console.log(data)
            // showPage(data);
            $listResults.empty();
            showList(data);
        }
    });
}

globalThis.page = 1;

function findPage(page) {
    globalThis.page = page;
    getInquiryList();
}

/* 카테고리 바꿨을 때 */ /*민구버전*/
// $("#filter-select").on("change", function () {
//     let val;
//     if ($(this).val() === "RECENT") val = null;
//     val = $(this).val();
//
//     parentsBoardSearch.categoryType = val;
//
//     getInquiryList();
// });
/*

console.log(inquirySearch.categoryType+"카아테에고오리이");
/!* 카테고리 바꿨을 때 *!/ /!*동한 버전*!/
$("#filter-select").on("change", function () {
    let val;
    if ($(this).val() === "ALL") {
        val = null;
    } else {
        val = $(this).val();
    }
    console.log(val + "123456789");
    inquirySearch.categoryType = val;
    getInquiryList();
});
*/

// 검색 조건 별 수행 민구버젼
// $("form[name='search-form']").on("submit", function (e) {
//     e.preventDefault();
//     let val;
//
//     // 빈 문자열이면 검색 수행 안됨
//     let $search = $(".search-input");
//     if($search.val() === "") return;
//
//     val = $search.val();
//
//     parentsBoardSearch.searchTitle = val;
//
//     getParensBoardList();
// });


// 동한 버전
$("form[name='search-form']").on("submit", function (e) {
    e.preventDefault();
    let val;
 /*   let searchSelected = $(".filter-for-serach").val(); //검색 구분*/
    let $search = $("#searchbox");
    if ($search.val() === "") return;

    val = $search.val();

 /*   if (searchSelected === "title") {
        parentsBoardSearch.searchTitle = val;
        parentsBoardSearch.searchContent = "null";
    } else if (searchSelected === "titleContents") {
        parentsBoardSearch.searchContent = val;
        parentsBoardSearch.searchTitle = val;
    }*//*
    console.log(inquirySearch.searchContent + "777");
    console.log(inquirySearch.searchTitle + "888");*/
    getInquiryList();
    // parentsBoardSearch.searchContent = "null";
    // parentsBoardSearch.searchTitle = "null";
});

// 검색 옵션 바뀔 때마다 검색어 창 비우기
/*
$(".filter-for-serach").on("change", function () {
    $(".search-input").val("");
});
*/


// 페이지 불러오기
function showPage(data) {
    let pageable = data.pageable;
    let pageNumber = pageable.pageNumber;
    let totalPages = data.totalPages;
    let count = Math.floor(pageNumber / PAGE_AMOUNT);

    let startPage = count * PAGE_AMOUNT;
    let endPage = startPage + PAGE_AMOUNT;

    endPage = endPage > data.totalPages ? data.totalPages : endPage;

    let hasPrev = startPage > 1;
    // 170 page / 5 = 24 -> 171 /
    let hasNext = endPage < data.totalPages;

    let text = "";


    // Previous button
    if (hasPrev) {
        text += '<li class="paging-list-item">';
        text += `<button class="paging-btn-prev" onclick="findPage(${startPage})" data-page = "${pageNumber}" aria-label="이전 목록">`;
        text += '<div class="paging-btn-prev-image-wrapper">';
        text += '<img class="paging-btn-prev-image" src="/images/parents-yard-board/parents-yard-board/paging-left.png">';
        text += '</div>';
        text += '</button>';
        text += '</li>';
    }

    // Page buttons
    for (let i = startPage + 1; i < endPage + 1; i++) {
        let page = i;
        text += '<li class="paging-list-item">';
        if (pageNumber + 1 == page) {
            text += `<button type="button" onclick="findPage(${i})" class="paging-list-item-btn active">${i}</button>`;
        } else {
            text += `<button type="button" onclick="findPage(${i})" class="paging-list-item-btn">${i}</button>`;
        }
        text += '</li>';
    }

    // Next button
    if (hasNext) {
        text += '<li class="paging-list-item">';
        text += `<button class="paging-btn-next" onclick="findPage(${endPage + 1})" data-page="' + (pageNumber + 1) + '" aria-label="다음 목록">`;
        text += '<div class="paging-btn-next-image-wrapper">';
        text += '<img class="paging-btn-next-image" src="/images/parents-yard-board/parents-yard-board/paging-right.png">';
        text += '</div>';
        text += '</button>';
        text += '</li>';
    }

    text += '</ul>';

    $pageWrap.html(text);
}

//    문의사항 목록
function showList(data) {
    console.log(data)
    console.log("showList 들어옴");
    let inquiryDTOS = data.content;
    inquiryDTOS.each((i, inquiry) => {
        // const formattedDate = formatDate(new Date(inquiry.parentsBoardRegisterDate));
        console.log("text 들어옴");
        var text = "";
        text += `
      <tr>
        <td>
          <div class="checkbox-wrapper-21">
            <label class="control control--checkbox">
              <input type="checkbox" id="select-all" class="substituted select-member" style="display: none;" />
              <div class="control__indicator"></div>
            </label>
          </div>
        </td>
        <td class="numbers">${inquiry.id}</td>
        <td>${inquiry.inquiryType}</td>
         <td>${inquiry.inquiryContent}</td>
        <td>${inquiry.inquiryEmail}</td>
        <td>${inquiry.inquiryPhoneNumber}</td>
        <td>${inquiry.inquiryStatus}</td>
      </tr>
    `;
        $listResults.append(text);

});
}

getInquiryList();

