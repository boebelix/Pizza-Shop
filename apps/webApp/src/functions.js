function showLogin() {
    document.getElementById("overlay_login").style.display = "block";
}

function showSignup() {
    document.getElementById("overlay_signup").style.display = "block";
}

function hideLogin() {
    document.getElementById("overlay_login").style.display = "none";
}

function hideSignup() {
    document.getElementById("overlay_signup").style.display = "none";
}

function showMain() {
    document.getElementById("main_page").style.display = "block";
}

function hideMain() {
    document.getElementById("main_page").style.display = "none";
}

const createIngredientButtons = (ingredientList) => {
    const toppingTable = document.getElementById("topping_list");
    let tableContent = "<tr>";
    let index = 1;
    for(ingredient of ingredientList) {
        let ingredientCheckbox = "<input type=\"checkbox\" id=\"ingredient_" + ingredient + "\" name=\"ingredient_" + ingredient + "\" class=\"ingredientCheckbox\"/>";
        ingredientCheckbox += "<label for=\"ingredient_" + ingredient + "\">" + ingredient + "</label>";
        tableContent += "<td>" + ingredientCheckbox + "</td>";
        if(index % 4 == 0) {
            tableContent += "</tr><tr>"
        }
        index++;
    }
    toppingTable.innerHTML = tableContent.substr(0, tableContent.length - 5);
}

const onSizeButtonClicked = (buttonSelected) => {
    return () => {
            document.querySelector(".button_size_selected").classList.remove("button_size_selected");
            buttonSelected.classList.add("button_size_selected");
    }
}

window.onload = () => {
    const buttons = document.getElementsByClassName("button_size");
    for(button of buttons) {
        button.addEventListener("click", onSizeButtonClicked(button));
    }
    createIngredientButtons(["abc", "def", "ghi", "jkl", "mno"]);
}