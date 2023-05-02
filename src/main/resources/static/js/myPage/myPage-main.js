var img = document.getElementById('myImg');
var modal = document.getElementById('myModal');
var closeButton = document.getElementsByClassName('close')[0];

// When the image is clicked, open the modal
img.onclick = function() {
    modal.style.display = "block";
}

// When the close button is clicked, close the modal
closeButton.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}