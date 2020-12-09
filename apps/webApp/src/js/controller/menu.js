const initMenu = () => {
	document.getElementById("main_order_button").addEventListener("click", () => setState(STATE_ORDER));
	document.getElementById("main_overview_button").addEventListener("click", () => setState(STATE_ORDER_HISTORY));
	document.getElementById("main_profile_button").addEventListener("click", () => setState(STATE_PROFILE));
}
