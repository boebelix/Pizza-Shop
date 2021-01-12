const initOrder = async() => {
	document.getElementById("ordered_back_button").addEventListener("click", () => {setState(STATE_MENU); CURRENT_ORDER = []; reset();});
	document.getElementById("order_continue_button").addEventListener("click", () => continuePressed());

	await fetchIngredients();
	await fetchSizes();

	createIngredientButtons();
	createPizzaSizeButtons();

	const buttons = document.getElementsByClassName("button_size");
	for (button of buttons) {
		button.addEventListener("click", onSizeButtonClicked(button));
	}

	const checkboxes = document.getElementsByClassName("ingredientCheckbox");
	for (checkbox of checkboxes) {
		checkbox.addEventListener("click", onToppingCheckboxClicked(checkbox));
	}

	reset();
	updatePrices();
}

const reset = () => {
	const checkboxes = document.getElementsByClassName("ingredientCheckbox");
	for (checkbox of checkboxes) {
		checkbox.checked = false;
	}
}

const continuePressed = () => {
	let toppingList = [];
	const checkboxes = document.getElementsByClassName("ingredientCheckbox");
	for (checkbox of checkboxes) {
		if(checkbox.checked) {
			toppingList.push({"toppingId": getTopping(checkbox.name).id, "amount": 1});
		}
	}
	CURRENT_ORDER.push({"sizeId": getCurrentSizeId(), "toppings": toppingList});
	reset();
	setState(STATE_ORDER_OVERVIEW);
}

const onSizeButtonClicked = (buttonSelected) => {
	return () => {
		document.querySelector(".button_size_selected").classList.remove("button_size_selected");
		buttonSelected.classList.add("button_size_selected");
		updatePrices();
	}
}

const onToppingCheckboxClicked = (checkbox) => {
	return () => {
		updatePrices();
	}
}

const fetchIngredients = async () => {
	let shopResponse = await response(SERVER_ADDRESS_SHOP + SERVER_INGREDIENT, "", TYPE_GET, HEADER_BASIC);
	for (let i = 0; i < shopResponse.length; i++) {
		TOPPINGS.push(shopResponse[i]);
	}
}

const createIngredientButtons = () => {
	const toppingTable = document.getElementById("topping_list");
	let tableContent = "<tr>";
	for (let i = 0; i < TOPPINGS.length; i++) {
		let name = TOPPINGS[i].name;
		let ingredientCheckbox = `<input type="checkbox" id="ingredient_${name}" name="${TOPPINGS[i].id}" class="ingredientCheckbox"/>`;
		ingredientCheckbox += `<label for="ingredient_${name}"\>${name}</label>`;
		tableContent += `<td>${ingredientCheckbox}</td>`;
		if ((i + 1) % 3 == 0) {
			tableContent += "</tr><tr>";
		}
	}
	// If ends with opening tr from loop remove it, else add a closing tr
	if (tableContent.endsWith("<tr>")) {
		toppingTable.innerHTML = tableContent.substr(0, tableContent.length - 5);
	} else {
		toppingTable.innerHTML = tableContent + "</tr>";
	}
}

const fetchSizes = async () => {
	let shopResponse = await response(SERVER_ADDRESS_SHOP + SERVER_SIZE, "", TYPE_GET, HEADER_BASIC);
	for (let i = 0; i < shopResponse.length; i++) {
		SIZES.push(shopResponse[i]);
	}
}

const createPizzaSizeButtons = () => {
	let sizeButtonDiv = document.querySelector("#pizza_size_buttons");
	let buttonText = "";

	for(let i = 0; i < SIZES.length; i++) {
		let secondclass = "";
		if(i == Math.floor(SIZES.length / 2)) {
			secondclass = " button_size_selected";
		}
		buttonText += `<button name="${SIZES[i].id}" class=\"button_size${secondclass}\">${SIZES[i].radius} Ø</button>`;
	}

	sizeButtonDiv.innerHTML = buttonText;
}

const updatePrices = () => {
	let currentSize = getSize(getCurrentSizeId());
	let basePrice = currentSize.basePrice;
	let toppingPrice = currentSize.toppingPrice;
	document.querySelector("#basic_price").innerHTML = basePrice + "€";
	let checkboxList = document.getElementsByClassName("ingredientCheckbox");
	let extraPrice = 0.0;
	for(let i = 0; i < checkboxList.length; i++) {
		if(checkboxList[i].checked) {
			extraPrice += toppingPrice;
		}
	}
	document.querySelector("#extra_price").innerHTML = extraPrice + "€";
	document.querySelector("#total_price").innerHTML = (basePrice + extraPrice) + "€";
}

const getCurrentSizeId = () => {
	return document.querySelector(".button_size_selected").name;
}
