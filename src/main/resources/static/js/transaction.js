function fillFieldsFromSelectedCard() {
    var select = document.getElementById("selectSenderCard");
    var selectedCard = select.options[select.selectedIndex];

    document.getElementById("senderCardNumber").value = selectedCard.querySelector("span:nth-child(1)").textContent;
    document.getElementById("senderCvv").value = selectedCard.querySelector("span:nth-child(2)").textContent;
    document.getElementById("senderExpirationDate").value = selectedCard.querySelector("span:nth-child(3)").textContent;
}

function switchMode() {
    var fillFieldsMode = document.getElementById("fillFieldsMode");
    var selectCardMode = document.getElementById("selectCardMode");
    var switchModeButton = document.getElementById("switchModeButton");

    fillFieldsMode.style.display = fillFieldsMode.style.display === "none" ? "block" : "none";
    selectCardMode.style.display = selectCardMode.style.display === "none" ? "block" : "none";
    switchModeButton.innerText = fillFieldsMode.style.display === "none" ? "Switch to Fill Fields Mode" : "Switch to Select Card Mode";
}