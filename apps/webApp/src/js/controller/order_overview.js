const initOrderOverview = () => {
	document.getElementById("final_add_button").addEventListener("click", () => setState(STATE_ORDER));
	document.getElementById("final_order_button").addEventListener("click", () => setState(STATE_ORDER_HISTORY));
	document.getElementById("final_back_button").addEventListener("click", () => setState(STATE_MENU));
}
