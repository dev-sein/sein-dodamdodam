// /*페이지 클릭 이벤트 active */
// $(document).ready(function() {
//     // const $paginationBtns = $(".page");
//     const $paginationBtns = $(".page-button");
//     // $paginationBtns.click(function(e) {
//     $paginationBtns.on("click", function(e) {
//         e.preventDefault();
//         $('.active').removeClass('active');
//         // 현재 클릭된 버튼에 active 클래스를 추가합니다.
//         $(this).addClass('active');
//         const page = $(this).text(); // 클릭한 페이지 번호 가져오기
//         globalThis.page = parseInt(page);
//         adminList(page);
//     })
//     adminList(1);
// });
// /*목록 출력*/
// $(document).ready(function() {
//     $.ajax({
//         url: `/admins/inquiry/list/${page}`,
//         success: function(result) {
//             console.log("success들어옴");
//             console.log(result); //사용할 content 값 출력
//             result.content.forEach((inquiry) => showList(inquiry)); //목록 출력 함수 실행
//             showPage(result);
//             console.log("adminlist");
//         },
//         error: function(xhr, status, error) {
//             console.error('Error:', error);
//         }
//     });
// });
//
// /*문의 목록*/
// function showList(inquiry) {
//     const $listResults = $("#inquiryTable tbody");
//     var text = "";
//     console.log("showlist");
//         console.log("들어옴");
//         text += `
//             <tr>
//                 <td>
//                     <!-- 체크박스 -->
//                     <div class="checkbox-wrapper-21">
//                         <label class="control control--checkbox">
//                             <input type="checkbox" id="select-all" class="substituted select-member" style="display: none;" />
//                             <div class="control__indicator"></div>
//                         </label>
//                     </div>
//                     <!-- 체크박스 -->
//                 </td>
//                 <td class="numbers">${inquiry.id}</td>
//                 <td>${inquiry.inquiryType}</td>
//                 <td>${inquiry.inquiryEmail}</td>
//                 <td>${inquiry.inquiryPhoneNumber}</td>
//                 <td>${inquiry.inquiryContent}</td>
//                 <td>${inquiry.inquiryStatus}</td>
//             </tr>
//         `;
//     $listResults.append(text);
// }
// // $(document).ready(function() {
// //     // 페이지 번호 클릭 이벤트 처리
// //     $(".page").on("click", function () {
// //         page = $(this).text(); // 클릭한 페이지 번호 가져오기
// //     });
// // })
//     // 문의사항 목록 조회 함수
// // function loadInquiries() {
// //     $.ajax({
// //         url: '/admins/inquiry/list',
// //         type: 'get',
// //         // data: { page: page },
// //         dataType: 'json',
// //         success: function(result) {
// //             console.log('페이지 처리 성공');
// //             showList(result);
// //         },
// //         error: function(xhr, status, error) {
// //             console.error('Error:', error);
// //         }
// //     });
// // }
//
// const PAGE_AMOUNT = 10;
// const $pageWrap = $(".pages-wrapper");
//
// function adminList() {
//     console.log("함수 실행")
//     $.ajax({
//         url: `/admins/inquiry/list/${globalThis.page}`,
//         success: function(data) {
//             console.log("adminList들어옴");
//             showPage(data);
//             // $contentWrap.empty();
//         },
//         error: function(a, b, c){
//             console.log(a, b, c);
//         }
//     })
// }
//
// globalThis.page = 1;
//
// function findPage(page) {
//     globalThis.page = page;
//     adminList();
// }
//
// function showPage(data) {
//     let pageable = data.pageable;
//     let pageNumber = pageable.pageNumber;
//     let totalPages = data.totalPages;
//     let count = Math.floor(pageNumber/PAGE_AMOUNT);
//
//     let startPage = count * PAGE_AMOUNT;
//     let endPage = startPage + PAGE_AMOUNT;
//
//     endPage = endPage > data.totalPages ? data.totalPages : endPage;
//
//     let hasPrev = startPage > 1; //startpage가 1보다 크면
//     // 170 page / 5 = 24 -> 171 /
//     let hasNext = endPage < data.totalPages;
//     let text = "";
//
//
//     // Previous button
//     if (hasPrev) {
//         text += '<div class="">';
//         text += '<div class="page-button-margin">';
//         text += '<div>';
//         text += '<img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_left_48px-128.png" class="left-button" onclick="findPage(${startPage})" data-page = "${pageNumber}" aria-label="이전 목록">';
//         text += '</div>';
//         text += '</div>';
//         text += '</div>';
//     }
//
//     // Page buttons
//     for (let i = startPage + 1; i < endPage + 1; i++) {
//         let page = i;
//         if (pageNumber  + 1 == page) {
//             text += `<div class="page-button-active page-button" onclick="findPage(${i})">`;
//         } else {
//             text += `<div class="page-button" onclick="findPage(${i})">`;
//         }
//         text += `<div class="page-button-margin">`;
//         text += `<div>`;
//         text += `<span>${i}</span>`;
//         text += `</div>`;
//         text += `</div>`;
//         text += `</div>`;
//     }
//
//     // Next button
//     if (hasNext) {
//         text += `<div class="">`;
//         text += `<div class="page-button-margin">`;
//         text += `<div>`;
//         text += `<img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_right_48px-128.png" class="right-button" onclick="findPage(${endPage+1})" data-page="' + (pageNumber + 1) + '" aria-label="다음 목록">`;
//         text += `</div>`;
//         text += `</div>`;
//         text += `</div>`;
//     }
//     $pageWrap.html(text);
// }
//
// // adminList();


/*==========================================================================================*/

const PAGE_AMOUNT = 10;
// const SEARCH_URL = "/parentsYard/list/show";
const $pageWrap = $(".pages-wrapper");
const $contentWrap = $("#inquiryTable tbody");


function getAdminParentsBoardReplyList() {
    $.ajax({
        url: `list/${globalThis.page}`,
        type: 'get',
        success: function(data) {
            $pageWrap.empty();
            showPage(data);
            $contentWrap.empty();
            showList(data.content);
        }

    })
}

globalThis.page = 1;

function findPage(page) {
    globalThis.page = page;
    getAdminParentsBoardReplyList();
}



function showPage(data) {
    let pageable = data.pageable;
    let pageNumber = pageable.pageNumber;
    let totalPages = data.totalPages;
    let count = Math.floor(pageNumber/PAGE_AMOUNT);

    let startPage = count * PAGE_AMOUNT;
    let endPage = startPage + PAGE_AMOUNT;

    endPage = endPage > data.totalPages ? data.totalPages : endPage;

    let hasPrev = startPage > 1;
    // 170 page / 5 = 24 -> 171 /
    let hasNext = endPage < data.totalPages;

    let text = "";


    // Previous button
    if (hasPrev) {
        text += '<div class="">';
        text += '<div class="page-button-margin">';
        text += '<div>';
        text += '<img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_left_48px-128.png" class="left-button" onclick="findPage(${startPage})" data-page = "${pageNumber}" aria-label="이전 목록">';
        text += '</div>';
        text += '</div>';
        text += '</div>';
    }

    // Page buttons
    for (let i = startPage + 1; i < endPage + 1; i++) {
        let page = i;
        if (pageNumber  + 1 == page) {
            text += `<div class="page-button-active page-button" onclick="findPage(${i})">`;
        } else {
            text += `<div class="page-button" onclick="findPage(${i})">`;
        }
        text += `<div class="page-button-margin">`;
        text += `<div>`;
        text += `<span>${i}</span>`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
    }

    // Next button
    if (hasNext) {
        text += `<div class="">`;
        text += `<div class="page-button-margin">`;
        text += `<div>`;
        text += `<img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_right_48px-128.png" class="right-button" onclick="findPage(${endPage+1})" data-page="' + (pageNumber + 1) + '" aria-label="다음 목록">`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
    }

    $pageWrap.html(text);
}


// 목록
function showList(inquiry) {
    var content = "";
    inquiry.forEach(inquiry => {
        content +=
            `
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
    <td class="numbers">${inquiry.id}</td>
    <td>${inquiry.inquiryType}</td>
    <td>${inquiry.inquiryEmail}</td>
    <td>${inquiry.inquiryPhoneNumber}</td>
    <td>${inquiry.inquiryContent}</td>
    <td>${inquiry.inquiryStatus}</td>
    </tr>
    `;
    });
    $contentWrap.append(content);

}

getAdminParentsBoardReplyList();