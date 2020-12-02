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

	const output = await response(data);

	if (output.status == RESPONSE_OK) {
		document.getElementById("error_login").innerHTML = "";
		username.innerHTML = "";
		password.innerHTML = "";
		setCookie("uuid", output.token, 1);
		setUserData(output.user);
		console.log(USER_JSON);
	} else {
		document.getElementById("error_login").innerHTML = decodeUtf(output.message);
	}
}

const response = async (data) => {
	const output = await fetch(SERVER_ADDRESS+"/auth", {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	});
	const status = output.status;
	if (status == RESPONSE_INTERNAL_SERVER_ERROR) {
		return {"message": "Serverfehler", "status": RESPONSE_INTERNAL_SERVER_ERROR};
	}
	let json = await output.json();
	json.status = status;
	return json;
}

