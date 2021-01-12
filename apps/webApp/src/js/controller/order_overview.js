const initOrderOverview = () => {
	document.getElementById("final_add_button").addEventListener("click", () => setState(STATE_ORDER));
	document.getElementById("final_order_button").addEventListener("click", () => submitOrder());
	document.getElementById("final_back_button").addEventListener("click", () => {setState(STATE_MENU); CURRENT_ORDER = [];});
}

const submitOrder = async () => {
	if(!LOGGED_IN) {
		STATE_CALLBACK = stateID;
		setState(STATE_LOGIN);
		return;
	}
	let data = {"pizzas" : CURRENT_ORDER}
	let requestResponse = await response(SERVER_ADDRESS_SHOP + SERVER_SHOP, data, TYPE_POST, HEADER_UUID);
	if(requestResponse.status === 201) {
		CURRENT_ORDER = [];
		setState(STATE_ORDER_HISTORY);
	}
}
