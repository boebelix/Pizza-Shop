const isLengthValid = (id) => {
	const element = document.getElementById(id);
	if (element.value.length == 0) {
		element.classList.add("btn_error");
		return false;
	} else {
		element.classList.remove("btn_error");
		return true;
	}
}

const equalityValidator = (id_first, id_repeat, id_error, message) => {
	let ret = true;
	const first = document.getElementById(id_first);
	const second = document.getElementById(id_repeat);
	const error = document.getElementById(id_error);

	if (first.value.length == 0) {
		first.classList.add("btn_error");
		ret = false;
	}
	if (second.value.length == 0) {
		second.classList.add("btn_error");
		ret = false;
	}
	if (first.value != second.value) {
		first.classList.add("btn_error");
		second.classList.add("btn_error");
		error.innerHTML = message;
		ret = false;
	}

	if (ret) {
		first.classList.remove("btn_error");
		second.classList.remove("btn_error");
		error.innerHTML = "";
	}
	return ret;
}
