const setState = (stateID) => {
	const state = getStateByName(stateID);
	if(state.hasToBeLoggedIn && !LOGGED_IN) {
		STATE_CALLBACK = stateID;
		setState(STATE_LOGIN);
		return;
	}
	for (let i = 0; i < STATES.length; i++) {
		if (STATES[i].name != stateID) {
			document.getElementById(STATES[i].name).style.display = "none";
		} else {
			document.getElementById(STATES[i].name).style.display = "block";
		}
	}
	if(stateID != STATE_LOGIN && stateID != STATE_SIGNUP) {
		STATE_HISTORY = stateID;
	}
}

const initStates = () => {
	// Profile State
	initProfile();
	// Menu State
	initMenu();
	// Order History State
	initOrderHistory();
	// Order Overview State
	initOrderOverview();
	// Order State
	initOrder();
	// Login State
	initLogin();
	// Signup State
	initSignup();
}

const loadUserFromCookies = async () => {
	const UUID = getCookie("uuid");

	if(UUID === "") {
		return;
	}

	const header = {
		'Content-Type': 'application/json',
		'Authorization': UUID
	};
	const output = await response(SERVER_ADDRESS + SERVER_USER, "", TYPE_GET, header);
	if (output.status == RESPONSE_OK) {
		setUserData(output);
	}
}

window.onload = () => {
	setState(STATE_MENU);
	// Begin Test Data
	createIngredientButtons(["Tomate", "Salamai", "Mozarella", "Paprika", "Zwiebel"]);
	loadOrderHistory({
		"orders": [{"id": 1, "orderDate": "05.05.2020", "pizzaCount": 4}, {
			"id": 2,
			"orderDate": "04.05.2020",
			"pizzaCount": 2
		}, {"id": 3, "orderDate": "10.09.2020", "pizzaCount": 3}, {
			"id": 4,
			"orderDate": "04.11.2020",
			"pizzaCount": 1
		}, {"id": 5, "orderDate": "04.11.2020", "pizzaCount": 1}]
	});
	// End Test Data
	initStates();
	// Check if User is in Cookies
	loadUserFromCookies();
}

const decodeUtf = (message) => {
	let first_replace = message.replace(/ÃŸ/g, 'ß');
	let decoded_message;
	try {
		decoded_message = decodeURIComponent(escape(first_replace));
	} catch (e) {
		decoded_message = first_replace;
	}
	return decoded_message;
}


const response = async (url, data, type, header) => {
	let output;
	if(type !== TYPE_GET) {
		output = await fetch(url, {
			method: type,
			headers: header,
			body: JSON.stringify(data)
		});
	} else {
		output = await fetch(url, {
			method: type,
			headers: header
		});
	}

	const status = output.status;
	if (status == RESPONSE_INTERNAL_SERVER_ERROR) {
		return {"message": "Serverfehler", "status": RESPONSE_INTERNAL_SERVER_ERROR};
	}
	if (status == RESPONSE_NOT_FOUND) {
		return {"message": "Serverfehler", "status": RESPONSE_NOT_FOUND};
	}
	let json = await output.json();
	json.status = status;
	return json;
}

