const $listResults = $("#inquiryTable tbody");
let page = 0;

listService = (function() {
    function list(page, callback) {
        $.ajax({
            url: '/admins/inquiry/list-content',
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
//
// $(".page").on("click", "a", function(e) {
//     e.preventDefault();
//     console.log("page 들어옴");
//     $listResults.empty();
//     const targetPage = $(this).text();
//
//     if ($(this).hasClass("arrow")) {
//         if ($(this).attr("id") === "prev") {
//             page--;
//         } else if ($(this).attr("id") === "next") {
//             page++;
//         }
//     } else {
//         page = parseInt(targetPage) - 1;
//     }
//     getList(page);
// });
//
// function displayPagination(totalPages) {
//     const $pagination = $(".paging-layout");
//     $pagination.empty();
//
//     if (page > 0) {
//         $pagination.append("<a href='#' class='arrow' id='prev'><img class='prev'  src='/image/mypage/arrow.png'></a>");
//     }
//
//     for (let i = 1; i <= totalPages; i++) {
//         if (i === page + 1) {
//             $pagination.append("<div class='paging-border-none paging-active'><span class='page'>" + i + "</span></div>");
//         } else {
//             $pagination.append("<div class='paging-border-none'><a href='#' class='page'>" + i + "</a></div>");
//         }
//     }
//
//     if (page < totalPages - 1) {
//         $pagination.append("<a href='#' class='arrow' id='next'><img class='next' src='/image/mypage/arrow.png'></a>");
//     }
// }


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

    if (page > 0) {
        $pagination.append("<div class='arrow-left'></div>");
    }

    for (let i = 1; i <= totalPages; i++) {
        if (i === page + 1) {
            $pagination.append("<div class='page active'>" + i + "</div>");
        } else {
            $pagination.append("<div class='page'>" + i + "</div>");
        }
    }

    if (page < totalPages - 1) {
        $pagination.append("<div class='arrow-right'></div>");
    }
}


function listText(list) {
    console.log("list text 들어옴");
    let inquiryDTOS = list.content;
    $(inquiryDTOS).each((i, inquiry) => {
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
        <td>${inquiry.inquiryEmail}</td>
        <td>${inquiry.inquiryPhoneNumber}</td>
        <td>${inquiry.inquiryContent}</td>
        <td>${inquiry.inquiryStatus}</td>
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
