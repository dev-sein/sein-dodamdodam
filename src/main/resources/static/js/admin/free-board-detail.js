const $listResults = $(".inner-left-wrapper");

// 페이지 클릭 이벤트 active
$(document).ready(function() {
    adminDetail();
});

function adminDetail() {
    console.log("ajax 실행");
    $.ajax({
        url: `/admins/free-board/detail/{boardId}`,
        type: 'get',
        dataType: 'json',
        success: function(data) {
            console.log("success들어옴");
            showDetail(data.content);
            console.log(data);
        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
        }
    });
}

function showDetail(freeBoards) {
        var text = "";
        console.log("showdetail");
        text += `
             <div class="inner-right-title">첨부된 이미지</div>
                        <!-- 이미지 첨부 슬라이드 배너 -->
                        <div class="image-wrapper">
                            <!-- 페이지 표시 -->
                            <div class="page-wrapper">
                                <span class="now-page">1</span>
                                <span> / </span>
                                <span class="total-page">3</span>
                            </div>
                            <!-- 페이지 표시 -->
                            <div class="slider">
                                <div class="slider__container">
                                  <div class="slider__item">
                                    <!-- <img src="/images/admin/1.png" > -->
                                    <img src="https://cdn.wadiz.kr/wwwwadiz/green001/2023/0112/20230112203341104_181833.jpeg/wadiz/format/jpg/quality/80/" >
                                  </div>
                                  <div class="slider__item">
                                    <img src="https://cdn.wadiz.kr/wwwwadiz/green001/2023/0129/20230129210936952_181833.jpg/wadiz/format/jpg/quality/80/">
                                  </div>
                                </div>
                                <button class="slider__arrow slider__arrow--left">&lt;</button>
                                <button class="slider__arrow slider__arrow--right">&gt;</button>
                            </div>
                        </div>
                        <!-- 이미지 첨부 슬라이드 배너 -->
                    </div>
                    <div class="inner-right-wrapper">
                        <div class="inner-right-title">게시글 정보</div>
                        <div class="right-info-wrapper">
                            <div class="classification-wrapper">
                                <div class="classification">제목</div>
                                <div class="event-title info">자유 게시글 제목1</div>
                            </div>
                            <div class="classification-wrapper">
                                <div class="classification">작성자</div>
                                <div class="writer info">작성자1</div>
                            </div>
                            <div class="classification-wrapper">
                                <div class="classification">카테고리</div>
                                <div class="date info"><span class="seen-count">314</span>회</div>
                            </div>
                            <!-- <div class="classification-wrapper">
                                <div class="classification">좋아요</div>
                                <div class="recruit-numbers info"><span class="like-count">15</span>개</div>
                            </div> -->
                            <div class="classification-wrapper">
                                <div class="classification">작성 날짜</div>
                                <div class="date info">2023년 4월 25일 오후 5시 20분</div>
                            </div>
                            <div class="classification-wrapper">
                                <div class="classification">내용</div>
                                <div class="contents">
                                    <div class="contents-inner">
                                        ${freeBoard.boardContent}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
        `;
        $listResults.append(text);
}


