const initSignup = () => {
    document.getElementById("register_submit_button").addEventListener("click", () => setState("menu_state"));
    document.getElementById("register_back_button").addEventListener("click", () => setState("login_state"));
}
