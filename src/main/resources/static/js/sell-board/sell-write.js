
const fileArray = new Array();

document.querySelector(".btn-attach-thumb").addEventListener("click", function () {
    const currentImageCount = document.querySelectorAll(".file-add-boxes img").length;
    if (currentImageCount < 5) {
      document.getElementById("imgFile").click();
    } else {
      showModal();
    }
  });
  
  function attachImageClickListener(imageElement) {
    imageElement.addEventListener("click", function () {
      imageElement.parentElement.parentElement.remove();
      document.getElementById("imgFile").value = "";
    });
  }


  document.getElementById("imgFile").addEventListener("change", function (e) {
    const files = e.target.files;
    const fileBoxWrap = document.getElementById("fileBoxWrap");
    let formData = new FormData(); // input 태그 담는 폼


    for (let i = 0; i < files.length; i++) {
      const currentImageCount = document.querySelectorAll(".file-add-boxes img").length;
      const remainingImageSlots = 5 - currentImageCount;
  
      if (remainingImageSlots === 0) {
        showModal();
        break;
      }

      const file = files[i];
      const reader = new FileReader();

      formData.append("file", file);
  
      reader.onload = function (e) {
        const fileAddBox = document.createElement("div");
        fileAddBox.className = "file-add-boxes";
  
        const divAttachThumb = document.createElement("div");
        divAttachThumb.className = "div-attach-thumb";
  
        const btnAttachThumb = document.createElement("div");
        btnAttachThumb.className = "btn-attach-thumb";
  
        const img = document.createElement("img");
        img.src = e.target.result;
        img.style.width = "100%";
        img.style.height = "100%";
        img.style.position = "absolute";
        img.style.top = "0";
        img.style.left = "0";
  
        attachImageClickListener(img);
  
        btnAttachThumb.appendChild(img);
        divAttachThumb.appendChild(btnAttachThumb);
        fileAddBox.appendChild(divAttachThumb);
        fileBoxWrap.appendChild(fileAddBox);
      };
  
      reader.readAsDataURL(file);
  
      if (i >= remainingImageSlots - 1) {
        break;
      }
    }

    $.ajax({
      url: '/file/upload',
      data: data,
      method: 'post',
      processData: false,
      contentType: false,
      success: function (result) {
        if(result){
          result.forEach((result, i) => {
            let file = new Object();

            file.filePath = result.paths[i];
            file.fileUuid = result.uuids[i];
            file.fileOrgName = result.orgNames[i];

            fileArray.push(file);
          });
        }
      }
    });


  });
  
  function showModal() {
    const modal = document.getElementById("modal");
    modal.style.display = "block";
  
    const confirmBtn = document.getElementById("confirmBtn");
    confirmBtn.onclick = function () {
      modal.style.display = "none";
    };
  }


  /* 모든 항목 입력 확인 js */
  document.querySelector(".button-button").addEventListener("click", function () {
    this.preventDefault();

    const imgFiles = document.getElementById("imgFile").files;
    const inputTitle = document.querySelector(".input-content");
    const inputCount = document.querySelector(".input-content");
    const inputName = document.querySelector("#input-name");
    const inputPrice = document.querySelector(".input-content.price-margin");
    const contentTextarea = document.querySelector(".content-textarea");


    if (
        imgFiles.length === 0 ||
        inputTitle.value.trim() === "" ||
        inputCount.value.trim() === "" ||
        inputName.value.trim() === "" ||
        inputPrice.value.trim() === "" ||
        contentTextarea.value.trim() === ""
    ) {
        showModalWithMessage("필수 항목을 작성해주세요");
    } else {
        // 저장 로직을 여기에 추가해주세요.
        showModalWithMessage("판매글이 등록되었습니다");
        writeBoard();
    }
});

function showModalWithMessage(message) {
    const modal = document.getElementById("modal");
    const modalMessage = modal.querySelector("p");
    modalMessage.textContent = message;
    modal.style.display = "block";

    const confirmBtn = document.getElementById("confirmBtn");
    confirmBtn.onclick = function () {
        modal.style.display = "none";
    };
}



function writeBoard(){
  let $boardTitle = $('.input-title'); // 제목
  let $boardContent = $('.content-textarea'); // 내용
  let $productPrice = $('.price-margin'); // 가격
  let $productName = $('#input-name'); // 상품명
  let $productCount = $('.input-count'); // 상품수

  let purchaseBoardDTO = {
  boardTitle : $boardTitle.val(),
  boardContent : $boardContent.val(),
  productDTO : {
      productPrice : $productPrice.val().replaceAll(",", "").replaceAll(".", "").replaceAll("-", "").replaceAll(" ", ""),
      productName : $productName.val(),
      productCount : $productCount.val().replaceAll(",", "").replaceAll(".", "").replaceAll("-", "").replaceAll(" ", "")
    }
  };

  $.ajax({
    url: '/purchase/write',
    data: JSON.stringify(purchaseBoardDTO),
    method: 'post',
    processData: false,
    contentType: 'application/json',
    success: function() {
      location.href='/purchase/list';
    }
  });
}
  