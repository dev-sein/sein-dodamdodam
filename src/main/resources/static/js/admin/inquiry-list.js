
let page = 1;

$(document).ready(function() {
    $.ajax({
        url: '/admins/inquiry/list',
        type: 'post',
        dataType: 'json',
        success: function(result) {
            console.log("success들어옴");
            console.log(result); //사용할 content 값 출력
            result.content.forEach((inquiry) => showList(inquiry));
            loadInquiries();

        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
        }
    });
});
/*문의 목록*/
function showList(inquiry) {
    const $listResults = $("#inquiryTable tbody");
    var text = "";
    console.log("showlist");
    // inquiries.forEach(inquiry => {
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
       loadInquiries(); // 문의사항 목록 조회 함수 호출
    });
})
    // 문의사항 목록 조회 함수
function loadInquiries() {
    $.ajax({
        url: '/admins/inquiry/list',
        type: 'get',
        // data: { page: page },
        dataType: 'json',
        success: function(result) {
            console.log('페이지 처리 성공');
            showList(result);
        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
        }
    });
}




