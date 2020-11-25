const states = ["menu_state", "signup_state", "profile_state", "login_state", "order_overview_state", "order_history_state", "order_state"];

const setState = (stateID) => {
	for (let i = 0; i < states.length; i++) {
		if (states[i] != stateID) {
			document.getElementById(states[i]).style.display = "none";
		} else {
			document.getElementById(states[i]).style.display = "block";
		}
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
	const output = await loadUser(UUID);
	if (output.status == 200) {
		setUserData(output);
	}
}

window.onload = () => {
	setState("login_state");
	// Begin Test Data
	createIngredientButtons(["Tomate", "Salamai", "Mozarella", "Paprika", "Zwiebel"]);
	loadOrderHistory({ "orders": [{ "id": 1, "orderDate": "05.05.2020", "pizzaCount": 4 }, { "id": 2, "orderDate": "04.05.2020", "pizzaCount": 2 }, { "id": 3, "orderDate": "10.09.2020", "pizzaCount": 3 }, { "id": 4, "orderDate": "04.11.2020", "pizzaCount": 1 }, { "id": 5, "orderDate": "04.11.2020", "pizzaCount": 1 }] });
	// End Test Data
	initStates();
	// Check if User is in Cookies
	loadUserFromCookies();
}

const loadUser = async (UUID) => {
	const output = await fetch("http://localhost:9080/user", {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			'Authorization': UUID
		}
	});
	const status = output.status;
	if (status == 500) {
		return { "message": "Serverfehler", "status": 500 };
	}
	let json = await output.json();
	json.status = status;
	return json;
}
