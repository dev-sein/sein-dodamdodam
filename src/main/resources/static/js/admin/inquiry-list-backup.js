globalThis.page = 1;

// 페이지 클릭 이벤트 active
$(document).ready(function() {
    const $paginationBtns = $(".page-button");
    $paginationBtns.on("click", function(e) {
        e.preventDefault();
        $('.active').removeClass('active');
        $(this).addClass('active');
        const page = $(this).data("page");
        console.log(page + "페이지");
        adminList(page);
    });
    adminList(1);
});

function adminList(page) {
    console.log("함수 실행");
    $.ajax({
        url: `/admins/inquiry/list/${page}`,
        type: 'get',
        data: { page: page },
        dataType: 'json',
        success: function(data) {
            console.log("success들어옴");
            $listResults.empty();
            showPage(data);
            showList(data.content, page);
            console.log(data);
            console.log("adminList 페이지 출력 : " + page);
        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
        }
    });
}

const $listResults = $("#inquiryTable tbody");

function showList(inquiries, page) {
    inquiries.forEach((inquiry) => {
        console.log("showList 페이지 출력 : " + page);
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
                <td class="numbers">${inquiry.id}</td>
                <td>${inquiry.inquiryType}</td>
                <td>${inquiry.inquiryEmail}</td>
                <td>${inquiry.inquiryPhoneNumber}</td>
                <td>${inquiry.inquiryContent}</td>
                <td>${inquiry.inquiryStatus}</td>
            </tr>
        `;
        $listResults.append(text);
    });
}


const PAGE_AMOUNT = 10;
const $pageWrap = $(".pages-wrapper");


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


/*항목 삭제*/
$(document).ready(function() {
    // 삭제 버튼 클릭 시
    $('.delete-button').click(function() {
        var selectedItems = [];
        // 체크된 항목의 ID를 배열에 추가
        $('input.substituted.select-member:checked').each(function() {
            var inquiryId = $(this).closest('tr').find('.numbers').text();
            selectedItems.push(parseInt(inquiryId));
        });

        // 선택된 항목이 없는 경우 경고창을 표시하고 함수를 종료
        if (selectedItems.length === 0) {
            alert('삭제할 항목을 선택해주세요.');
            return;
        }
        $('#delete-modal').show(); //삭제 모달창 열기
        $('#confirm-btn').click(function() { //모달창의 확인 버튼 눌렀을 경우 데이터 삭제
                $.ajax({
                    url: '/admins/inquiry/delete',
                    type: 'DELETE',
                    contentType: 'application/json',
                    data: JSON.stringify(selectedItems),
                    success: function (response) {
                       // alert(response); // 서버로부터의 응답 메시지를 알림으로 표시(모달로 바꾸기)
                        location.reload() //삭제완료 후 새로고침
                    },
                    error: function (xhr, status, error) {
                        alert('오류가 발생했습니다. 다시 시도해주세요.');
                        console.log(error);
                    }
                });
        });
    });
});