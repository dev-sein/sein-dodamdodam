const $listResults = $("#pointTable tbody");
let page = 0;

listService = (function() {
    function list(page, callback) {
        $.ajax({
            url: '/admins/point/list-content',
            type: 'get',
            data: { page: page }, // 수정: page를 객체 형태로 전달
            success: function(list) {
                if (callback) {
                    console.log("들어옴");
                    callback(list);
                }
            }
        });
    }

    return {
        list: list
    };
})();

getList(page);


$(".pages-wrapper").on("click", ".page", function(e) {
    e.preventDefault();
    console.log("page 들어옴");
    $listResults.empty();
    const targetPage = $(this).text();

    if ($(this).hasClass("arrow-left")) {
        if (page > 0) {
            page--;
        }
    } else if ($(this).hasClass("arrow-right")) {
        page++;
    } else {
        page = parseInt(targetPage) - 1;
    }
    getList(page);
});


function displayPagination(totalPages) {
    const $pagination = $(".pages-wrapper");
    $pagination.empty();

    const maxDisplayedPages = 10; // 한 번에 표시할 페이지 수
    const startPage = Math.floor(page / maxDisplayedPages) * maxDisplayedPages; // 시작 페이지 번호

    if (page > 0) {
        $pagination.append("<div class='arrow-left page'></div>");
    }

    for (let i = startPage; i < startPage + maxDisplayedPages && i < totalPages; i++) {
        if (i === page) {
            $pagination.append("<div class='page active'>" + (i + 1) + "</div>");
        } else {
            $pagination.append("<div class='page'>" + (i + 1) + "</div>");
        }
    }

    if (page < totalPages - 1) {
        $pagination.append("<div class='arrow-right page'></div>");
    }
}


function listText(list) {
    console.log("list text 들어옴");
    let pointDTOS = list.content;
    $(pointDTOS).each((i, point) => {
        console.log("text 들어옴");
        var text = "";
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
    });
}

function getList(page) {
    console.log("getList 들어옴");
    listService.list(page, function(list) { // 수정: page를 인자로 전달
        listText(list);
        displayPagination(list.totalPages);
    });
}

