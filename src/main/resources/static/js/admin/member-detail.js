// /*const $detailResults = $("body");
// const $memberTable = $("#membertable");
//
// // 페이지 클릭 이벤트 active
// $(document).ready(function() {
//     adminDetail();
// });
//
//
// function adminDetail() {
//     console.log("ajax 실행");
//     $.ajax({
//         url: `/admins/member/detail/{memberId}`,
//         type: 'get',
//         dataType: 'json',
//         success: function(data) {
//             console.log("success들어옴");
//             showDetail(data.content);
//             console.log(data);
//         },
//         error: function(xhr, status, error) {
//             console.error('Error:', error);
//         }
//     });
// }
//
// function showDetail(member) {
//     var text = `
//         <div class="modal-whole">
//             <div class="modal">
//                 <div class="modal-header">
//                     <h2 class="modal-title">회원 정보</h2>
//                     <span class="close" onclick="closeModal()">&times;</span>
//                 </div>
//                 <div class="modal-body">
//                     <div class="member-profile">
//                         <div class="profile-image">
//                             <img src="../../static/images/admin/teacher.png">
//                         </div>
//                         <div class="member-info">
//                             <div class="info-row">
//                                 <div class="info-label">회원번호:</div>
//                                 <div class="info-value">${member.id}</div>
//                             </div>
//                             <div class="info-row">
//                                 <div class="info-label">이름:</div>
//                                 <div class="info-value">${member.memberName}</div>
//                             </div>
//                             <div class="info-row">
//                                 <div class="info-label">아이디:</div>
//                                 <div class="info-value">${member.memberId}</div>
//                             </div>
//                             <div class="info-row">
//                                 <div class="info-label">핸드폰번호:</div>
//                                 <div class="info-value">${member.phoneNumber}</div>
//                             </div>
//                             <div class="info-row">
//                                 <div class="info-label">이메일:</div>
//                                 <div class="info-value">${member.email}</div>
//                             </div>
//                             <div class="info-row">
//                                 <div class="info-label">주소:</div>
//                                 <div class="info-value">${member.address}</div>
//                             </div>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         </div>
//     `;
//     $detailResults.append(text);
// }
//
//
// $memberTable.on("click", "tr", function(e) {
//     console.log("클릭 이벤트 들어옴");
//     console.log($(e.target));
//     showModal();
// });
//
// $memberTable.on("click", "tr", function(e) {
//     console.log("클릭 이벤트 들어옴");
//     console.log($(e.target));
//     showModal(); // 모달을 열 때는 조건을 체크하지 않음
// });
//
//
// function showModal() {
//     $('.modal-whole').css('display', 'flex');
// }
//
// function closeModal() {
//     $('.modal-whole').css('display', 'none');
// }*/


const $detailResults = $("body");
const $memberTable = $("#membertable");

/*// 페이지 클릭 이벤트 active
$(document).ready(function() {
    adminDetail();
});*/

$memberTable.on("click", "tr", function(e) {
    console.log("클릭 이벤트 들어옴");
    console.log($(e.target));
    // showModal($(this).data("memberId")); // 클릭된 행의 memberId를 전달
    // showModal($(this).attr("data-member-id"));
    showModal($(this).find(".numbers").text());
});

function showModal(memberId) {
    $('.modal-whole').css('display', 'flex');
    $('.modal').css('display', 'flex');

    // memberId를 사용하여 서버에 AJAX 요청
    $.ajax({
        url: `/admins/member/detail/${memberId}`, // memberId를 URL에 포함시킴
        type: 'get',
        dataType: 'json',
        success: function(data) {
            console.log("success들어옴");
            showDetail(data); // 응답 받은 멤버 정보를 모달 내부에 출력
            console.log(data);
        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
        }
    });
}

function showDetail(member) {
    var text = "";
    console.log("showdetail");
    text += `
        <div class="modal-whole">
            <div class="modal" style="display:block;">
                <div class="modal-header">
                    <h2 class="modal-title">회원 정보</h2>
                    <span class="close" onclick="closeModal()">&times;</span>
                </div>
                <div class="modal-body">
                    <div class="member-profile">
                        <div class="profile-image">
                            <img src="../../static/images/admin/teacher.png">
                        </div>
                        <div class="member-info">
                            <div class="info-row">
                                <div class="info-label">회원번호:</div>
                                <div class="info-value">${member.id}</div>
                            </div>
                            <div class="info-row">
                                <div class="info-label">이름:</div>
                                <div class="info-value">${member.memberName}</div>
                            </div>
                            <div class="info-row">
                                <div class="info-label">아이디:</div>
                                <div class="info-value">${member.memberId}</div>
                            </div>
                            <div class="info-row">
                                <div class="info-label">핸드폰번호:</div>
                                <div class="info-value">${member.memberPhone}</div>
                            </div>
                            <div class="info-row">
                                <div class="info-label">이메일:</div>
                                <div class="info-value">${member.memberEmail}</div>
                            </div>
                            <div class="info-row">
                                <div class="info-label">포인트:</div>
                                <div class="info-value">${member.memberPoint}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `;
    $detailResults.append(text);
}
