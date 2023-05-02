let check=false;
$(".Checkbox_icon").on("click", $(".Checkbox_icon"), function(){
   /*check=$("#checkboxtf").is(":checked");*/
   console.log(check);
   if(!check){
      $("#realbutton").css('opacity','1');
      $("#checkboxmint").css('backgroundColor', '#006633');
      check = true;
   }else{
      $("#realbutton").css('opacity','0');
      $("#checkboxmint").css('backgroundColor', '#fff');
      check = false;
   }
});


/* 체크박스를 해야 된다는 모달 */
const checkbox = document.getElementById("checkboxtf");
const paymentButton = document.getElementById("paymentbutton");
const modal = document.getElementById("modal");
const modalButton = document.getElementById("modal-button");

paymentButton.addEventListener("click", function(event) {
   if (!checkbox.checked) {
      event.preventDefault(); // 기본 제출 방지
      modal.style.display = "block"; // 모달 보이기
   }
});

modalButton.addEventListener("click", function() {
   modal.style.display = "none"; // 버튼을 클릭하면 모달 숨기기
});


