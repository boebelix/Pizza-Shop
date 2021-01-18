let USER_JSON = {};
let LOGGED_IN = false;
let TOPPINGS = [];
let SIZES = [];
let CURRENT_ORDER = [];
const RESPONSE_OK = 200;
const RESPONSE_INTERNAL_SERVER_ERROR = 500;
const RESPONSE_NOT_FOUND = 400;
const MIN_SIGNS = 5;
const SAFE_SIGNS = 7;
const CANVAS_HEIGHT = 10;
const CANVAS_WIDTH = 240;
const CANVAS_FACTOR = 0.4;
const REGEX_SIGNS = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
const PASSWORD_ERROR_EQUALITY = "Passwörter stimmen nicht überein";
const EMAIL_ERROR_EQUALITY = "E-mail-Adressen stimmen nicht überein";
const SERVER_ADDRESS_USER = "http://localhost:9084";
const SERVER_ADDRESS_SHOP = "http://localhost:9083";
const SERVER_AUTH = "/auth";
const SERVER_USER = "/user";
const SERVER_INGREDIENT = "/topping";
const SERVER_SIZE = "/size";
const SERVER_SHOP = "/shop";
const TYPE_POST = 'POST';
const TYPE_PUT = 'PUT';
const TYPE_GET = 'GET';
const HEADER_BASIC = {
    'Content-Type': 'application/json'
};
let HEADER_UUID = {
    'Content-Type': 'application/json',
    'Authorization': ''
};

const STATE_MENU = "menu_state";
const STATE_SIGNUP = "signup_state";
const STATE_PROFILE = "profile_state";
const STATE_LOGIN = "login_state";
const STATE_ORDER_OVERVIEW = "order_overview_state";
const STATE_ORDER_HISTORY = "order_history_state";
const STATE_ORDER = "order_state";

const STATES = [
    new State(STATE_MENU, false),
    new State(STATE_SIGNUP, false),
    new State(STATE_PROFILE, true),
    new State(STATE_LOGIN, false),
    new State(STATE_ORDER_OVERVIEW, false),
    new State(STATE_ORDER_HISTORY, true),
    new State(STATE_ORDER, false)
];

let STATE_HISTORY = STATE_MENU;
let STATE_CALLBACK = STATE_MENU;


const setUserData = (JSON) => {
    USER_JSON = JSON;
    document.getElementById("username_menu").innerHTML = USER_JSON.firstName;
    document.getElementById("street_profile").value = USER_JSON.street;
    document.getElementById("number_profile").value = USER_JSON.number;
    document.getElementById("city_profile").value = USER_JSON.city;
    document.getElementById("postalcode_profile").value = USER_JSON.postalCode;
    document.getElementById("country_profile").value = USER_JSON.country;
    LOGGED_IN = true;
    setState(STATE_CALLBACK);
}

const setCookie = (cname, cvalue, exdays) => {
    if (exdays === 0) {
        LOGGED_IN = false;
    }
    HEADER_UUID.Authorization = cvalue;
    let d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    let expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

const getCookie = (cname) => {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

const getSize = (id) => {
    for (let i = 0; i < SIZES.length; i++) {
        if (SIZES[i].id == id) {
            return SIZES[i];
        }
    }
    return null;
}

const getTopping = (id) => {
    for (let i = 0; i < TOPPINGS.length; i++) {
        if (TOPPINGS[i].id == id) {
            return TOPPINGS[i];
        }
    }
    return null;
}