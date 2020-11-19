const entriesPerRow = 4;

const initOrderHistory = () => {
    document.getElementById("order_back_button").addEventListener("click", () => setState("menu_state"));
    document.getElementById("order_continue_button").addEventListener("click", () => setState("order_overview_state"));
}

const loadOrderHistory = (orderJson) => {
    let historyHTML = "<tr>";
    for(let i = 0; i < orderJson.orders.length; i++) {
        historyHTML += `<td><div class="img_box" style="background-color: rgba(75, 75, 75, 0.486);"><div><h3>Bestellung #${orderJson.orders[i].id}`;
        historyHTML += `</h3><p><div class="inline">Bestellt am </div><div id="order_date" class="inline">${orderJson.orders[i].orderDate}`;
        historyHTML += `</div></p><p><div id="order_value" class="inline">${orderJson.orders[i].pizzaCount}`;
        historyHTML += "</div><div class=\"inline\">x Pizza</div></p></div></div></td>";
        if(i % entriesPerRow == entriesPerRow-1) {
            historyHTML += "</tr><tr>";
        }
    }
    // If ends with opening tr from loop remove it, else add a closing tr
    if(historyHTML.endsWith("<tr>")) {
        document.getElementById("ordered_div").innerHTML = historyHTML.substr(0, historyHTML.length-5);
    } else {
        document.getElementById("ordered_div").innerHTML = historyHTML + "</tr>";
    }
}