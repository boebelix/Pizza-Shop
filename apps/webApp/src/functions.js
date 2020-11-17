const states = ["main_page", "overlay_signup", "profile_container", "overlay_login", "final_order_container", "ordered_container", "order_container"];

function setState(stateID) {
    for(let i = 0; i < states.length; i++) {
        if(states[i] != stateID) {
            document.getElementById(states[i]).style.display = "none";
        } else {
            document.getElementById(states[i]).style.display = "block";
        }
    }
}

const createIngredientButtons = (ingredientList) => {
    const toppingTable = document.getElementById("topping_list");
    let tableContent = "<tr>";
    let index = 1;
    for(ingredient of ingredientList) {
        let ingredientCheckbox = "<input type=\"checkbox\" id=\"ingredient_" + ingredient + "\" name=\"ingredient_" + ingredient + "\" class=\"ingredientCheckbox\"/>";
        ingredientCheckbox += "<label for=\"ingredient_" + ingredient + "\">" + ingredient + "</label>";
        tableContent += "<td>" + ingredientCheckbox + "</td>";
        if(index % 3 == 0) {
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

const loadOrderHistory = (orderJson) => {
        let historyHTML = "<tr>";
        for(let i = 0; i < orderJson.orders.length; i++) {
            historyHTML += "<td><div class=\"img_box\" style=\"background-color: rgba(75, 75, 75, 0.486);\"><div><h3>Bestellung #" + orderJson.orders[i].id;
            historyHTML += "</h3><p><div class=\"inline\">Bestellt am </div><div id=\"order_date\" class=\"inline\">" + orderJson.orders[i].orderDate;
            historyHTML += "</div></p><p><div id=\"order_value\" class=\"inline\">" + orderJson.orders[i].pizzaCount;
            historyHTML += "</div><div class=\"inline\">x Pizza</div></p></div></div></td>";
            if(i % 4 == 3) {
                historyHTML += "</tr><tr>";
            }
        }
        document.getElementById("ordered_div").innerHTML = historyHTML.substr(0, historyHTML.length-5);
}

window.onload = () => {
    setState("overlay_login");
    const buttons = document.getElementsByClassName("button_size");
    for(button of buttons) {
        button.addEventListener("click", onSizeButtonClicked(button));
    }
    createIngredientButtons(["Tomate", "Salamai", "Mozarella", "Paprika", "Zwiebel"]);
    loadOrderHistory({"orders": [{"id": 1, "orderDate": "05.05.2020", "pizzaCount": 4}, {"id": 2, "orderDate": "04.05.2020", "pizzaCount": 2}, {"id": 3, "orderDate": "10.09.2020", "pizzaCount": 3}, {"id": 4, "orderDate": "04.11.2020", "pizzaCount": 1}]});
    // Profile State
    document.getElementById("profile_back_button").addEventListener("click", () => setState("main_page"));
    // Main State
    document.getElementById("main_order_button").addEventListener("click", () => setState("order_container"));
    document.getElementById("main_overview_button").addEventListener("click", () => setState("ordered_container"));
    document.getElementById("main_profile_button").addEventListener("click", () => setState("profile_container"));
    // Ordered State
    document.getElementById("order_back_button").addEventListener("click", () => setState("main_page"));
    document.getElementById("order_continue_button").addEventListener("click", () => setState("final_order_container"));
    // Final Order State
    document.getElementById("final_add_button").addEventListener("click", () => setState("order_container"));
    document.getElementById("final_order_button").addEventListener("click", () => setState("ordered_container"));
    document.getElementById("final_back_button").addEventListener("click", () => setState("main_page"));
    // Orders State
    document.getElementById("ordered_back_button").addEventListener("click", () => setState("main_page"));
    // Login State
    document.getElementById("login_submit_button").addEventListener("click", () => setState("main_page"));
    document.getElementById("login_register_button").addEventListener("click", () => setState("overlay_signup"));
    // Register State
    document.getElementById("register_submit_button").addEventListener("click", () => setState("main_page"));
    document.getElementById("register_back_button").addEventListener("click", () => setState("overlay_login"));
    
}