const initLogin = () => {
    document.getElementById("login_submit_button").addEventListener("click", () => setState("menu_state"));
    document.getElementById("login_register_button").addEventListener("click", () => setState("signup_state"));
}