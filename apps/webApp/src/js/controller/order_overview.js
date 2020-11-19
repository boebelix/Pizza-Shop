const initOrderOverview = () => {
    document.getElementById("final_add_button").addEventListener("click", () => setState("order_state"));
    document.getElementById("final_order_button").addEventListener("click", () => setState("order_history_state"));
    document.getElementById("final_back_button").addEventListener("click", () => setState("menu_state"));
}