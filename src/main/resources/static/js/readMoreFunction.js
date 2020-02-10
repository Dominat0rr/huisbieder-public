function readMoreFunction() {
    var btnText = document.getElementById("btn-meer-details");
    var omschrijvingPreview = document.getElementById("omschrijvingPreview");
    var omschrijving = document.getElementById("omschrijving");

    if (omschrijving.style.display === "none") {
        omschrijving.style.display = "inline";
        omschrijvingPreview.style.display = "none";
        btnText.innerHTML = "Minder details";
    }
    else {
        omschrijving.style.display = "none";
        omschrijvingPreview.style.display = "inline";
        btnText.innerHTML = "Meer details";
    }
}