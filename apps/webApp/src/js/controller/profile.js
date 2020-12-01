const initProfile = () => {
	document.getElementById("submitted_profile_change").innerHTML="";
	document.getElementById("error_profile_change").innerHTML="";
	document.getElementById("profile_back_button").addEventListener("click", () => setState("menu_state"));
	document.getElementById("logout_button").addEventListener("click", () => logout());
	document.getElementById("passwd_change").addEventListener("click", () => changePasswd());
}

const changePasswd = async () => {
	const newPasswd = document.getElementById("password_new_profile");
	const newPasswdRepeat = document.getElementById("password_new_profile_repeat");
	let error = document.getElementById("error_profile_change");
	let submitted = document.getElementById("submitted_profile_change");
	submitted.innerHTML="";
	error.innerHTML="";

	if(!passwordValidator(newPasswd.id, newPasswdRepeat.id)){
		console.log(newPasswd.value+" "+newPasswdRepeat.value);
		error.innerHTML = "Passwörter stimmen nicht überein";
	}else{
		const data = {"password": newPasswd.value};
		let response = await updateData('http://localhost:9080/user/'+USER_JSON.username, data);

		if (response.status != RESPONSE_OK){
			error.innerHTML = response.message;
		}else{
			submitted.innerHTML = "Passwort erfolgreich geändert";
			error.innerHTML = "";
		}
	}
}

const updateData = async (url, data) => {
	const output = await fetch(url, {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json',
			'Authorization': getCookie("uuid")
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

const logout = () => {
	setCookie("uuid", "", 0);
	setState("login_state");
}
