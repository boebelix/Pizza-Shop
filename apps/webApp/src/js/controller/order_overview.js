const initOrderOverview = () => {
    document.getElementById("final_add_button").addEventListener("click", () => setState(STATE_ORDER));
    document.getElementById("final_order_button").addEventListener("click", () => submitOrder());
    document.getElementById("final_back_button").addEventListener("click", () => {
        setState(STATE_MENU);
        CURRENT_ORDER = [];
    });
}


const updateOverviewPrice = () => {
    let totalPrice = 0.0;
    const pizzaOrderDiv = document.getElementById("pizza_order_overview");
    pizzaOrderDiv.innerHTML = "";
    for (let i = 0; i < CURRENT_ORDER.length; i++) {
        totalPrice += parseFloat(CURRENT_ORDER[i].price);
        let toppingList = "";
        for (let j = 0; j < CURRENT_ORDER[i].toppings.length; j++) {

            if (j > 0) {
                toppingList += ", ";
            }
            toppingList += getTopping(CURRENT_ORDER[i].toppings[j].toppingId).name;
        }
        pizzaOrderDiv.innerHTML += "Pizza " + (i + 1) + " (" + getSize(CURRENT_ORDER[i].sizeId).diameter + "Ø, " + CURRENT_ORDER[i].price + "€): " + toppingList + "<br>";
    }
    pizzaOrderDiv.innerHTML += "<br>";
    document.getElementById("total_price_overview").innerHTML = totalPrice.toFixed(2) + "€";
}
const submitOrder = async() => {
    if (!LOGGED_IN) {
        STATE_CALLBACK = stateID;
        setState(STATE_LOGIN);
        return;
    }
    let data = { "pizzas": CURRENT_ORDER }
    let requestResponse = await response(SERVER_ADDRESS_SHOP + SERVER_SHOP, data, TYPE_POST, HEADER_UUID);
    if (requestResponse.status === 201) {
        CURRENT_ORDER = [];
        setState(STATE_ORDER_HISTORY);
    }
}