document.addEventListener("DOMContentLoaded", function () {
    const cardCarousels = document.querySelectorAll(".card-carousel");

    cardCarousels.forEach(function (carousel) {
        const cardsWrapper = carousel.querySelector(".card-wrapper");
        const cards = carousel.querySelectorAll(".card");
        const prevButton = carousel.querySelector(".prev-button");
        const nextButton = carousel.querySelector(".next-button");
        let currentIndex = 0;
        let cardWidth = cards[0].offsetWidth + 10; /* Учтите отступы между картами */

        function updateCarousel() {
            cardsWrapper.style.transform = `translateX(-${cardWidth * currentIndex}px)`;
        }

        prevButton.addEventListener("click", function () {
            if (currentIndex > 0) {
                currentIndex--;
                updateCarousel();
            }
        });

        nextButton.addEventListener("click", function () {
            if (currentIndex < cards.length - 1) {
                currentIndex++;
                updateCarousel();
            }
        });

        cards.forEach(function (card) {
            card.addEventListener("click", function () {
                this.classList.toggle("flipped"); /* Добавил "this" перед classList */
            });
        });
    });
});





