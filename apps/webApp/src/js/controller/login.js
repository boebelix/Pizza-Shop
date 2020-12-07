const initLogin = () => {
	document.getElementById("login_submit_button").addEventListener("click", () => login());
	document.getElementById("login_register_button").addEventListener("click", () => setState("signup_state"));
}

const login = async () => {
	if (!isLengthValid("username_login") | !isLengthValid("password_login")) {
		return;
	}
	const username = document.getElementById("username_login");
	const password = document.getElementById("password_login");

	const data = {
		"username": username.value,
		"password": password.value
	}

	const output = await response(SERVER_ADDRESS+SERVER_AUTH,data, TYPE_POST, HEADER_BASIC);

	if (output.status == RESPONSE_OK) {
		document.getElementById("error_login").innerHTML = "";
		username.innerHTML = "";
		password.innerHTML = "";
		setCookie("uuid", output.token, 1);
		setUserData(output.user);
	} else {
		document.getElementById("error_login").innerHTML = decodeUtf(output.message);
	}
}

