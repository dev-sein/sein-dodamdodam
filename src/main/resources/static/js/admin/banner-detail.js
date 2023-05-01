const container = document.querySelector('.slider__container');
  
  container.addEventListener('click', (event) => {
    const target = event.target;
    if (target.tagName === 'IMG') {
      const link = target.parentElement.querySelector('a');
      const clickEvent = new MouseEvent('click');
      link.dispatchEvent(clickEvent);
    }
  });