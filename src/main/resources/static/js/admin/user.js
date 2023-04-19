$(document).ready(function() {
    // 페이지네이션 버튼 요소들을 선택합니다.
    const $paginationBtns = $(".page");

    // 페이지네이션 버튼 요소에 이벤트 리스너를 등록합니다.
    $paginationBtns.click(function() {
        // 현재 active 클래스가 적용된 버튼의 active 클래스를 제거합니다.
        $('.active').removeClass('active');

        // 현재 클릭된 버튼에 active 클래스를 추가합니다.
        $(this).addClass('active');
    })
});

function showModal(){
    $('.modal-whole').css('display', 'flex');
}

function closeModal(){
    $('.modal-whole').css('display', 'none');
}


