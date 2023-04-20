function test() {
  var checkbox = document.getElementById("dropConfirm");
  var btnDropOut = document.getElementById("btnDropOut");

  if (checkbox.checked == true) {
    btnDropOut.disabled = false;
  } else {
    btnDropOut.disabled = true;
  }
}