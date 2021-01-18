const initProfile = () => {
	document.getElementById("profile_back_button").addEventListener("click", () => setState(STATE_MENU));
	document.getElementById("logout_button").addEventListener("click", () => logout());
	document.getElementById("passwd_change").addEventListener("click", () => changePasswd());
	document.getElementById("email_change").addEventListener("click", () => changeEmail());
	document.getElementById("address_change").addEventListener("click", () => changeAddress());
}

const changeAddress = async () => {
	let error = document.getElementById("error_profile_change");
	let submitted = document.getElementById("submitted_profile_change");
	const street = document.getElementById("street_profile");
	const number = document.getElementById("number_profile");
	const postalcode = document.getElementById("postalcode_profile");
	const city = document.getElementById("city_profile");
	const country = document.getElementById("country_profile");
	submitted.innerHTML = "";
	error.innerHTML = "";

	if (isLengthValid(street.id) & isLengthValid(number.id) & isLengthValid(postalcode.id) &
		isLengthValid(city.id) & isLengthValid(country.id)) {
		const data = {
			"street": street.value,
			"number": number.value,
			"postalCode": postalcode.value,
			"city": city.value,
			"country": country.value
		};
		let headerAuth = {
			'Content-Type': 'application/json',
			'Authorization': getCookie("uuid")
		};
		let responseAnswer = await response(SERVER_ADDRESS_USER + SERVER_USER + "/" + USER_JSON.userId, data, TYPE_PUT, headerAuth);

		if (responseAnswer.status != RESPONSE_OK) {
			error.innerHTML = decodeUtf(responseAnswer.message);
		} else {
			submitted.innerHTML = "Adresse erfolgreich geändert";
			error.innerHTML = "";
		}
	}
}

const changeEmail = async () => {
	let error = document.getElementById("error_profile_change");
	let submitted = document.getElementById("submitted_profile_change");
	const email = document.getElementById("email_profile");
	const emailRepeat = document.getElementById("email_profile_repeat");
	submitted.innerHTML = "";
	error.innerHTML = "";

	if (equalityValidator(email.id, emailRepeat.id, error.id, EMAIL_ERROR_EQUALITY)) {
		const data = {"email": email.value};
		let headerAuth = {
			'Content-Type': 'application/json',
			'Authorization': getCookie("uuid")
		};
		let responseAnswer = await response(SERVER_ADDRESS_USER + SERVER_USER + "/" + USER_JSON.userId, data, TYPE_PUT, headerAuth);

		if (responseAnswer.status != RESPONSE_OK) {
			error.innerHTML = decodeUtf(responseAnswer.message);
		} else {
			submitted.innerHTML = "Email erfolgreich geändert";
			error.innerHTML = "";
		}
	}
}

const changePasswd = async () => {
	const newPasswd = document.getElementById("password_new_profile");
	const newPasswdRepeat = document.getElementById("password_new_profile_repeat");
	let error = document.getElementById("error_profile_change");
	let submitted = document.getElementById("submitted_profile_change");
	submitted.innerHTML = "";
	error.innerHTML = "";

	if (equalityValidator(newPasswd.id, newPasswdRepeat.id, error.id, PASSWORD_ERROR_EQUALITY)) {
		const data = {"password": newPasswd.value};
		let headerAuth = {
			'Content-Type': 'application/json',
			'Authorization': getCookie("uuid")
		};
		let responseAnswer = await response(SERVER_ADDRESS_USER + SERVER_USER + "/" + USER_JSON.userId, data, TYPE_PUT, headerAuth);

		if (responseAnswer.status != RESPONSE_OK) {
			error.innerHTML = decodeUtf(responseAnswer.message);
		} else {
			submitted.innerHTML = "Passwort erfolgreich geändert";
			error.innerHTML = "";
		}
	}
}

const logout = () => {
	setCookie("uuid", "", 0);
	setState(STATE_MENU);
}
