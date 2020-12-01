const initMenu = () => {
	document.getElementById("main_order_button").addEventListener("click", () => setState("order_state"));
	document.getElementById("main_overview_button").addEventListener("click", () => setState("order_history_state"));
	document.getElementById("main_profile_button").addEventListener("click", () => setState("profile_state"));
	document.getElementById("bgvid").addEventListener('ended', () => removeVideoAndOverlay());
}

const removeVideoAndOverlay = () => {
	document.getElementById("videoDiv").style.display = "none";
}
