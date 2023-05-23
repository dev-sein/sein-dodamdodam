// 맨위로 가기 버튼
const backToTopButton = document.querySelector(".back-to-top");

window.addEventListener("scroll", () => {
    // 페이지 스크롤 위치가 500 이상이면 버튼을 보여줌
    if (window.pageYOffset > 500) {
        backToTopButton.style.display = "block";
    } else {
        backToTopButton.style.display = "none";
    }
});

backToTopButton.addEventListener("click", () => {
    // 버튼을 클릭하면 다시 클릭할 수 없도록 설정
    backToTopButton.disabled = true;

    // 맨 위로 스크롤
    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });

    // 스크롤이 끝나면 버튼을 다시 활성화
    setTimeout(() => {
        backToTopButton.disabled = false;
    }, 1000);
});