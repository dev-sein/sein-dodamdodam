// const $ul = $(".challenge-wrapper-list");
const $listResults = $("#inquiryTable tbody");
// 초기 페이지
let page = 0;
// 초기 status
// let challengeStatus= "OPEN";

listService = (function () {
    // 리스트를 불러오는 함수
    function list(page, callback) {
        $.ajax({
            url: '/admins/inquiry/list-content',
            type: 'get',
            data: page,
            success: function (list) {
                if (callback) {
                    console.log("들어옴");
                    callback(list);
                }
            }
        });
    }

    return {
        list: list,
    }
})();

// getList(challengeStatus, page);
getList(page);

$(".currently-challenge").click(() => {
    // $ul.empty();
    $listResults.empty();
    page = 0;
    // challengeStatus = "OPEN";
    getList(status, page);
    getList(page);
});

$(".closing-challenge ").click(() => {
    page = 0;
    // challengeStatus = "PRIVATE";
    // $ul.empty();
    $listResults.empty();
    // getList(status, page);
    getList(page);
});


$(".paging-layout").on("click", "a", function(e) {
    e.preventDefault();
    // $ul.empty();
    $ul.empty();
    const targetPage = $(this).text();

    if ($(this).hasClass("arrow")) {
        // 화살표 클릭 시 이전 페이지 또는 다음 페이지로 이동
        if ($(this).attr("id") === "prev") {
            page--;
        } else if ($(this).attr("id") === "next") {
            page++;
        }
    } else {
        // 페이지 번호 클릭 시 해당 페이지로 이동
        page = parseInt(targetPage) - 1;
    }

    // getList(challengeStatus, page);
    getList(page);
});

// =====================================================================================================
function displayPagination(totalPages) {
    const $pagination = $(".paging-layout");
    $pagination.empty();

    if (page > 0) {
        $pagination.append("<a href='#' class='arrow' id='prev'><img class='prev'  src='/image/mypage/arrow.png'></a>");
    }

    for (let i = 1; i <= totalPages; i++) {
        if (i === page + 1) {
            // 현재 페이지를 텍스트로 표시
            $pagination.append(
                "<div class='paging-border-none paging-active'><span class='page'>" + i + "</span></div>"
            );
        } else {
            // 다른 페이지는 a 태그로 표시
            $pagination.append("<div class='paging-border-none'><a href='#' class='page'>" + i + "</a></div>");
        }
    }
    if (page < totalPages - 1) {
        $pagination.append("<a href='#' class='arrow' id='next'><img class='next' src='/image/mypage/arrow.png'></a>");
    }
}

function listText(list) {
    console.log("list text 들어옴");
    // let personalChallengeDTOS = list.content;
    let inquiryDTOS = list.content;
    $(inquiryDTOS).each((i, inquiry) => {
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
};
    // $(personalChallengeDTOS).each((i, personalChallengeDTO) => {
    //     let text = `
    //                                             <li smcolumn="2" lgcolumn="3" class="challenge-board">
    //                                                     <div class="challenge-board-box">
    //                                                         <a href="/board/challenge/personal/detail/${personalChallengeDTO.id}"  class="challenge-board-box-a">
    //                                                             <div class="challenge-board-box-a-box">
    //                                                                 <div class="board-box">
    //                                                                     <span class="board-box-span-1 board-box-span-2">
    //                                                                         <picture class="board-box-picture" ratio="0.75">
    //                                                                         `;
    //     if(personalChallengeDTO.challengeDTO.fileDTOS.length == 0 || personalChallengeDTO.challengeDTO.fileDTOS == null || personalChallengeDTO.challengeDTO.fileDTOS == undefined){
    //         text += `<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/480px-No_image_available.svg.png" class="no-image">`;
    //     }else {
    //         for (let  j= 0; j < personalChallengeDTO.challengeDTO.fileDTOS.length; j++){
    //             if(personalChallengeDTO.challengeDTO.fileDTOS[j].fileType === "REPRESENTATIVE"){
    //                 text += `<img class="list-image"  src="/file/display?fileName=${personalChallengeDTO.challengeDTO.fileDTOS[j].filePath}/${personalChallengeDTO.challengeDTO.fileDTOS[j].fileUuid}_${personalChallengeDTO.challengeDTO.fileDTOS[j].fileOriginalName}">`;
    //             }
    //         }
    //     }
    //
    //     text +=                                        `        </picture>
    //                                                                     </span>
    //                                                                 </div>
    //                                                                 <div class="">
    //                                                                     <div class="board-container-1">
    //                                                                         <p class="board-container-name">${personalChallengeDTO.challengeDTO.boardTitle}</p>
    //                                                                     </div>
    //                                                                     <div class="board-container-2">${personalChallengeDTO.challengeDTO.boardContent}</div>
    //                                                                     <div class="board-container-3">
    //                                                                         <div class="board-container-set">
    //                                                                             <div class="board-container-set-wrap">
    //                                                                                 <div class="board-container-set-box">
    //                                                                                     <img src="/image/board/human-icon.png" alt="" class="human-icon">
    //                                                                                    <span>${personalChallengeDTO.attendCount}</span>
    //                                                                                 </div>
    //                                                                             </div>
    //                                                                             <div class="board-container-set-like" data-test-group="product-card-like-count">
    //                                                                                 <div class="board-container-set-box">
    //                                                                                     <img src="/image/board/reply-icon.png" alt="" class="reply-icon">
    //                                                                                     <span>${personalChallengeDTO.replyCount}</span>
    //                                                                                 </div>
    //                                                                             </div>
    //                                                                         </div>
    //                                                                     </div>
    //                                                                 </div>
    //                                                                 <div class="board-box-line"></div>
    //                                                                 <div class="board-box-line-flex"></div>
    //                                                             </div>
    //                                                         </a>
    //                                                     </div>
    //                                                 </li>
    //
    //     `;
        // $ul.append(text);



// function getList(status, page){
function getList(page){
    console.log("getList 들어옴");
    listService.list({
        page: page
        // challengeStatus : challengeStatus
    }, function (list) {
        // window.scrollTo(0, 0);
        listText(list);
        displayPagination(list.totalPages);
    })
}