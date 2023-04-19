function test() {
    const agreeCheckbox = document.querySelector('input[name="agree"]');
    const dropOutButton = document.getElementById('btnDropOut');
  
    if (agreeCheckbox.checked) {
      dropOutButton.disabled = false;
      dropOutButton.style.backgroundColor = '#e54545';
      dropOutButton.style.borderColor = '#e54545';
      dropOutButton.style.color = '#fff';
    } else {
      dropOutButton.disabled = true;
      dropOutButton.style.backgroundColor = '';
      dropOutButton.style.borderColor = '';
      dropOutButton.style.color = '';
    }
  }
  