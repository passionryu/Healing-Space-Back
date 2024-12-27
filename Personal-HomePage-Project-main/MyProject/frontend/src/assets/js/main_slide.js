let index = 0;
const slides = document.querySelectorAll('.slide');
const slideTrack = document.querySelector('.slide-track');
const totalSlides = slides.length;

function moveSlide() {
    index++;
    if (index >= totalSlides) {
        index = 0; // 마지막 슬라이드 후 첫 번째 슬라이드로 돌아감
    }
    slideTrack.style.transform = `translateX(-${index * 100}%)`;
}

setInterval(moveSlide, 4000); // 4초마다 슬라이드 이동
