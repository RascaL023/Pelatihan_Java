const popupBox = document.querySelector(".popup-box");
const popupContainer = document.querySelector(".popup-container");

setTimeout(() => {
    popupBox.classList.remove("slide-right");
    popupBox.classList.add("slide-out");

    setTimeout(() => popupContainer?.remove(), 350);
}, 3000);

