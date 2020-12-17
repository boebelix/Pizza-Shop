package ateam.shop.db;

import ateam.db.DBConnection;
import ateam.model.entity.Order;
import ateam.model.entity.Pizza;
import ateam.model.entity.PizzaTopping;
import ateam.model.entity.Topping;
import ateam.model.exception.ShopException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.List;

@Singleton
public class DBManager {

	@Inject
	private DBConnection connector;

	@Inject
	private PizzaToppingDB pizzaTopping;

	@Inject
	private OrdersDB orders;

	@Inject
	private PizzasDB pizzas;

	@Inject
	private SizesDB sizes;

	@Inject
	private ToppingsDB toppings;

	/*
	 *Nimmt Order Objekt und fügt was noch nicht vorhanden ist in Datenbank ein
	 */
	public void placeOrder(Order order) {
		try {
			int orderID = this.orders.insertNewOrder(order);

			for (Pizza pizza : order.getPizzas()) {

				int pizzaID = pizzas.createPizzaEntry(pizza);

				for (Topping t : pizza.getToppings())
					pizzaTopping.createPizzaToppingEntry(new PizzaTopping(pizzaID, t.getId(), 1));//1 ist ein Dummy Value, ich sah in der API nicht, dass die Menge übertragen wird
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ShopException("Couldn't add Pizza to existing Order", e);
		}
	}

	public Order createOrderObjectFromDB(int orderId) {

		try {
			Order order = orders.getOrderById(orderId);


			for (Pizza pizza : pizzas.getPizzaByOrderId(order.getId())) {

				List<PizzaTopping> pizzaToppings = pizzaTopping.getPizzaToppingByPizzaId(pizza.getId());

				for (PizzaTopping toppingId : pizzaToppings) {
					pizza.addTopping(toppings.getToppingById(toppingId.getToppingId()));
				}

				order.addPizza(pizza);
			}

			return order;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ShopException("Couldn't fetch Order from Database", e);
		}

	}
}
