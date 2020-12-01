const initProfile = () => {
	document.getElementById("profile_back_button").addEventListener("click", () => setState("menu_state"));
	document.getElementById("logout_button").addEventListener("click", () => logout());
}

const logout = () => {
	setCookie("uuid", "", 0);
	setState("login_state");
}
