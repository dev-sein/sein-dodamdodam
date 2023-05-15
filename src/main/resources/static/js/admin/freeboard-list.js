
let page = 1;

$(document).ready(function() {
    $.ajax({
        url: '/admins/free-board/list',
        type: 'post',
        dataType: 'json',
        success: function(result) {
            console.log("success들어옴");
            console.log(result); //사용할 content 값 출력
            result.content.forEach((e) => showList(e));
            // loadPages();

        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
        }
    });
});
/*문의 목록*/
function showList() {
    const $listResults = $("#free-board-Table tbody");
    var text = "";
    console.log("showlist");
        console.log("들어옴");
        text += `
            <tr>
                <td>
                    <!-- 체크박스 -->
                    <div class="checkbox-wrapper-21">
                        <label class="control control--checkbox">
                            <input type="checkbox" id="select-all" class="substituted select-member" style="display: none;" />
                            <div class="control__indicator"></div>
                        </label>
                    </div>
                    <!-- 체크박스 -->
                </td>
                <td class="numbers">${freeboard.id}</td>
                <td>${freeboard.boardTitle}</td>
                <td>${freeboard.memberName}</td>
                <td>${freeboard.categoryType}</td>
                <td>${freeboard.createdDate}</td>
                <!-- <td>${point.pointStatus}</td> -->
                <!-- <td>2000.01.01 21:05:04</td>-->
                <!-- <td><button class="show-detail" onclick="showModal()">상세보기</button></td> -->
            </tr>
        `;
    // });
    $listResults.append(text);
}
$(document).ready(function() {
    // 페이지 번호 클릭 이벤트 처리
    $(".page").on("click", function () {
        page = $(this).text(); // 클릭한 페이지 번호 가져오기
        // loadPages(); // 문의사항 목록 조회 함수 호출
    });
})
//     // 문의사항 목록 조회 함수
// function loadPages() {
//     $.ajax({
//         url: '/admins/point/list',
//         type: 'get',
//         // data: { page: page },
//         dataType: 'json',
//         success: function(result) {
//             console.log('페이지 처리 성공');
//             showList(result);
//         },
//         error: function(xhr, status, error) {
//             console.error('Error:', error);
//         }
//     });
// }


// const doSearch = (searchProduct, page) => {
//     let pageNum;
//     if(page) {
//         pageNum = $(page).data("page");
//         searchProduct.page = pageNum;
//     }

//     console.log(searchProduct);

//     $doAjax("GET", SEARCH_URL, searchProduct, (result) => {
//         console.log(result);
//         $itemCount.text(result.totalElements);

//         // 결과 append 전 내용 비우기
//         $itemWrap.empty();

//         // 결과 append
//         result.content.forEach((e) => appendList(e));

//         // 페이징 버튼 생성
//         showPage(result);
//     });
// }

// // 페이지 로딩 시 검색 요청
// doSearch({});

// function showPage(result) {
//     let pageable = result.pageable;
//     let pageNumber = pageable.pageNumber;
//     let count = Math.floor(pageNumber / PAGE_AMOUNT);
//     // 7 page / 5 -> floor(1.4) = 1 -> 1 * 5 + 1 -> startPage = 6
//     let startPage = count * PAGE_AMOUNT;
//     let endPage = startPage + PAGE_AMOUNT;

//     endPage = endPage > result.totalPages ? result.totalPages : endPage;

//     console.log("end page : " + endPage);

//     let hasPrev = startPage > 1;
//     // 170 page / 5 = 24 -> 171 /
//     let hasNext = endPage < result.totalPages;

//     // 페이징 HTML 태그를 추가하는 코드 작성
//     let paging = "";
//     paging += `<div class="paging" style="text-align: center">`;

//     if (hasPrev) {
//         paging += `<a class="changePage" data-page="${startPage}" 
//                         onclick="preventDefault(this)"><span><</span></a>`;
//     }
//     for (let i = startPage + 1; i < endPage + 1; i++) {
//         let page = i;
//         if (pageNumber + 1 != page) {
//             paging += `<a class="changePage" data-page="${page}" onclick="doSearch(searchProduct, this)"><span>${page}</span></a>`;
//         } else {
//             paging += `<span id="currentPage">${page}</span>`;
//         }
//     }
//     if (hasNext) {
//         paging += `<a class="changePage" data-page="${endPage}" onclick="doSearch(searchProduct)"><span>></span></a>`
//     }

//     $('.paging-div').html(paging);
// }

