package Model;

import ateam.model.entity.*;
import ateam.model.exception.UserServiceException;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class DBManager {

	Connection connection;

	PizzaOrderDB pizzaOrder;
	OrdersDB orders;
	PizzasDB pizzas;
	PizzaToppingOrderDB pizzaToppingOrder;
	SizesDB sizes;
	ToppingsDB toppings;

	public DBManager() {
		DBConnector connector=new DBConnector();
		try {
			connection = connector.getConnection();
		}catch(SQLException e)
		{
			System.out.println("Unable to connect to database");
		}
		pizzaOrder=new PizzaOrderDB(connection);

		orders=new OrdersDB(connection);

		pizzas=new PizzasDB(connection);

		pizzaToppingOrder=new PizzaToppingOrderDB(connection);

		sizes=new SizesDB(connection);

		toppings=new ToppingsDB(connection);
	}


	/*
	*Nimmt Order Objekt und fügt was noch nicht vorhanden ist in Datenbank ein
	*/

	public void placeOrder(Orders order)
	{
		try {
			int orderID = this.orders.insertNewOrder(order);

			for (Pizzas pizza : order.getPizzas()) {

				int pizzaID = pizzas.createPizzaEntry(pizza);

				PizzaOrder pizzaOrderDTO = new PizzaOrder(orderID, pizzaID);
				int pizzaOrderId = pizzaOrder.createEntry(pizzaOrderDTO);

				for (Toppings t : pizza.getToppings())
					pizzaToppingOrder.createPizzaToppingOrderEntry(new PizzaOrderTopping(pizzaOrderId, t.getId(), 1));//1 ist ein Dummy Value, ich sah in der API nicht, dass die Menge übertragen wird
			}
		}catch (SQLException e){
			e.printStackTrace();
			throw new UserServiceException("Couldn't add Pizza to ExistingOrder", e);
		}
	}

	public Orders createOrderObjectFromDB(int OrderId){

		try {
			Orders order = orders.getOrderById(OrderId);

			for (int id : pizzaOrder.getPizzasByOrderId(OrderId)) {
				Pizzas pizza = pizzas.getPizzaById(id);

				List<Toppings> toppingsList = new LinkedList<Toppings>();

				List<PizzaOrderTopping> connectionList = pizzaToppingOrder.getPizzaToppingByPizzaOrderId(OrderId);

				for (PizzaOrderTopping toppingId : connectionList) {
					pizza.addTopping(toppings.getToppingById(toppingId.getToppingId()));
				}

				order.addPizza(pizza);
			}

			return order;
		}catch (SQLException e){
			e.printStackTrace();
			throw new UserServiceException("Couldn't fetch Order from Database", e);
		}

	}
}
