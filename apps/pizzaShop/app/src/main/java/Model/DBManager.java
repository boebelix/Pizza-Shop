package Model;

import ateam.DBConnection.DBConnector;
import ateam.model.entity.Orders;
import ateam.model.entity.PizzaTopping;
import ateam.model.entity.Pizzas;
import ateam.model.entity.Toppings;
import ateam.model.exception.ShopException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Singleton
public class DBManager {

	@Inject
	DBConnector connector;

	private Connection connection;

	private PizzaToppingDB pizzaTopping;
	private OrdersDB orders;
	private PizzasDB pizzas;
	private SizesDB sizes;
	private ToppingsDB toppings;

	public DBManager() {

		try {
			connection = connector.getConnection();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database");
		}
		pizzaTopping = new PizzaToppingDB(connection);

		orders = new OrdersDB(connection);

		pizzas = new PizzasDB(connection);

		sizes = new SizesDB(connection);

		toppings = new ToppingsDB(connection);
	}


	/*
	 *Nimmt Order Objekt und fügt was noch nicht vorhanden ist in Datenbank ein
	 */

	public void placeOrder(Orders order) {
		try {
			int orderID = this.orders.insertNewOrder(order);

			for (Pizzas pizza : order.getPizzas()) {

				int pizzaID = pizzas.createPizzaEntry(pizza);

				for (Toppings t : pizza.getToppings())
					pizzaTopping.createPizzaToppingEntry(new PizzaTopping(pizzaID, t.getId(), 1));//1 ist ein Dummy Value, ich sah in der API nicht, dass die Menge übertragen wird
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ShopException("Couldn't add Pizza to ExistingOrder", e);
		}
	}

	public Orders createOrderObjectFromDB(int OrderId) {

		try {
			Orders order = orders.getOrderById(OrderId);


			for (Pizzas pizza : pizzas.getPizzaByOrderId(order.getId())) {

				List<PizzaTopping> pizzaToppings = pizzaTopping.getPizzaToppingByPizzaId(pizza.getID());

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
