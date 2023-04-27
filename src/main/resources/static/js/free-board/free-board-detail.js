/* 수정, 삭제 버튼  */
const comment = document.querySelector(".comment-util");
const commentList = document.querySelector(".comment-util-list");

comment.addEventListener("click", e => {
    e.preventDefault();
    $(commentList).slideToggle();
});

// function showModal(){
//     $('.modal-whole').css('display', 'flex');
// }

// function closeModal(){
//     $('.modal-whole').css('display', 'none');
// }

.wcommunity-share-area .btn-symp.active {
    color: #F98F9B;
    border-color: #F98F9B;
    background-image: url(/resources/static/img/common/icon_red_heart.png);
}


