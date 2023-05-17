globalThis.page = 1;

// 페이지 클릭 이벤트 active
$(document).ready(function() {
    const $paginationBtns = $(".page-button-margin .page");
    $paginationBtns.on("click", function(e) {
        // $paginationBtns.click(function(e) {
        e.preventDefault();
        $('.active').removeClass('active');
        // 현재 클릭된 버튼에 active 클래스를 추가합니다.
        $(this).addClass('active');
        const page = $(this).data("page");
        console.log(page+"페이지");
        adminList(page); // 페이지 번호를 전달하여 adminList 함수 호출
    })
    adminList(1); // 초기 페이지 로드 시 첫 번째 페이지 데이터 조회
});

/*목록 출력*/
const $listResults = $("#pointTable tbody");
function showList(point) {
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
                <td class="numbers">${point.id}</td>
                <td>${point.memberId}</td>
                <td>${point.memberName}</td>
                <td>${point.pointAmount}</td>
                <td>${point.createdDate}</td>
                <td>${point.pointStatus}</td>
                <!-- <td>2000.01.01 21:05:04</td>-->
                <!-- <td><button class="show-detail" onclick="showModal()">상세보기</button></td> -->
            </tr>
    `;
    $listResults.append(text);
}

const PAGE_AMOUNT = 10;
const $pageWrap = $(".pages-wrapper");

function adminList(page) {
    console.log("함수 실행")
    $.ajax({
        url: `/admins/point/list/${page}`,
        type: 'get',
        data: { page: page },
        dataType: 'json',
        success: function(result) {
            console.log("success들어옴");
            // $listResults.empty();
            result.content.forEach((point) => showList(point)); //목록 출력 함수
            console.log(result); // 사용할 JSON 데이터 출력, 페이지 선택을 해도 데이터 contents에서 계속 1페이지 데이터로 출력됨
            showPage(result); //페이지 실행 함수
            console.log("adminlist");

        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
        }
    });
}

function findPage(page) {
    adminList(page);
}

function showPage(data) {
    let pageable = data.pageable;
    let pageNumber = pageable.pageNumber;
    let totalPages = data.totalPages;
    let count = Math.floor(pageNumber / PAGE_AMOUNT);

    let startPage = count * PAGE_AMOUNT;
    let endPage = startPage + PAGE_AMOUNT;

    endPage = endPage > data.totalPages ? data.totalPages : endPage;


    let hasPrev = startPage > 1;
    let hasNext = endPage < data.totalPages;
    let text = "";

    // Previous button
    if (hasPrev) {
        text += `
        <div class="">
            <div class="page-button-margin">
                <div>
                    <img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_left_48px-128.png" class="left-button" onclick="findPage(${startPage})" data-page="${pageNumber}" aria-label="이전 목록">
                </div>
            </div>
        </div>
    `;
    }

// Page buttons
    for (let i = startPage; i < endPage; i++) {
        let page = i + 1;
        if (pageNumber + 1 == page) {
            text += `<div class="page-button-active page-button" onclick="findPage(${page})" data-page="${pageNumber}">`;
        } else {
            text += `<div class="page-button" onclick="findPage(${page})" data-page="${pageNumber}">`;
        }
        text += `<div class="page-button-margin">`;
        text += `<div>`;
        text += `<span>${page}</span>`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
    }

// Next button
    if (hasNext) {
        text += `<div class="">`;
        text += `<div class="page-button-margin">`;
        text += `<div>`;
        text += `<img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_right_48px-128.png" class="right-button" onclick="findPage(${endPage + 1})" data-page="' + (pageNumber + 1) + '" aria-label="다음 목록">`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
    }

    $pageWrap.html(text);
}
// adminList();


