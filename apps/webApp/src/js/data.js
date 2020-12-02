var USER_JSON = {};
const RESPONSE_OK = 200;
const RESPONSE_INTERNAL_SERVER_ERROR = 500;
const MIN_SIGNS = 5;
const SAFE_SIGNS = 7;
const CANVAS_HEIGHT = 10;
const CANVAS_WIDTH = 240;
const CANVAS_FACTOR = 0.4;
const REGEX_SIGNS = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
const PASSWORD_ERROR_EQUALITY = "Passwörter stimmen nicht überein";
const EMAIL_ERROR_EQUALITY = "E-mail-Adressen stimmen nicht überein";
const SERVER_ADDRESS = "http://localhost:9080";

const setUserData = (JSON) => {
	USER_JSON = JSON;
	document.getElementById("username_menu").innerHTML = USER_JSON.firstName;
	document.getElementById("street_profile").value = USER_JSON.street;
	document.getElementById("number_profile").value = USER_JSON.number;
	document.getElementById("city_profile").value = USER_JSON.city;
	document.getElementById("postalcode_profile").value = USER_JSON.postalCode;
	document.getElementById("country_profile").value = USER_JSON.country;
	setState("menu_state");
}

const setCookie = (cname, cvalue, exdays) => {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

const getCookie = (cname) => {
	var name = cname + "=";
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}
