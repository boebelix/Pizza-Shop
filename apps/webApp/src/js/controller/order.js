const initOrder = () => {
	document.getElementById("ordered_back_button").addEventListener("click", () => setState("menu_state"));

	const buttons = document.getElementsByClassName("button_size");
	for (button of buttons) {
		button.addEventListener("click", onSizeButtonClicked(button));
	}
}

const onSizeButtonClicked = (buttonSelected) => {
	return () => {
		document.querySelector(".button_size_selected").classList.remove("button_size_selected");
		buttonSelected.classList.add("button_size_selected");
	}
}

const createIngredientButtons = (ingredientList) => {
	const toppingTable = document.getElementById("topping_list");
	let tableContent = "<tr>";
	let index = 1;
	for (ingredient of ingredientList) {
		let ingredientCheckbox = `<input type="checkbox" id="ingredient_${ingredient}" name="ingredient_${ingredient}" class="ingredientCheckbox"/>`;
		ingredientCheckbox += `<label for="ingredient_${ingredient}"\>${ingredient}</label>`;
		tableContent += `<td>${ingredientCheckbox}</td>`;
		if (index % 3 == 0) {
			tableContent += "</tr><tr>";
		}
		index++;
	}
	// If ends with opening tr from loop remove it, else add a closing tr
	if (tableContent.endsWith("<tr>")) {
		toppingTable.innerHTML = tableContent.substr(0, tableContent.length - 5);
	} else {
		toppingTable.innerHTML = tableContent + "</tr>";
	}
}
