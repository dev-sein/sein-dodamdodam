// $('.add-image').on('click', addImage(this));

function addImage(e){
    console.log(e);
    console.log($(e).next());
    $(e).next().click();
}

function imageUpload(obj){
    const fileReader = new FileReader();

    if(!obj.files.length) {
        console.log("return 들어옴");
        return;
    }
    // .add-image 클래스 삭제
    $(obj).prev().removeClass();

    fileReader.readAsDataURL(obj.files[0]);
    console.log(obj.files[0]);
    fileReader.onload = function(e){
        console.log(e);
        console.log(e.target);
        $(obj).prev().attr('src', e.target.result);
        console.log("들어옴");
    }
    // $(e).prev().attr("src", $(e).val());
}

/* form 밖에 있는 버튼으로 제출 */
$("#submit-button").on('click',function(){
    console.log("들어옴");
    document.bannerForm.submit();
});

// JavaScript
// const bannerItems = document.querySelectorAll('.banner__item');

// function addImage(element) {
//   const input = element.nextSibling;
//   input.click();
//   input.onchange = function() {
//     const image = input.files[0];
//     const reader = new FileReader();
//     reader.onload = function(e) {
//       const previewImage = element.parentNode.querySelector('.preview-image');
//       previewImage.src = e.target.result;
//     }
//     reader.readAsDataURL(image);
//   }
// }

// bannerItems.forEach(function(item) {
//   const addImageBtn = item.querySelector('.add-image');
//   addImageBtn.addEventListener('click', function() {
//     addImage(addImageBtn);
//   });
// });
