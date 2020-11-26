package Model;

import ateam.model.entity.Orders;
import ateam.model.entity.PizzaOrder;
import ateam.model.entity.Pizzas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class DBManager {
	Connection connection;
	PizzaOrderDB pizzaOrder;
	OrdersDB orders;
	PizzasDB pizzas;
	PizzaToppingDB pizzaTopping;
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

		pizzaTopping=new PizzaToppingDB(connection);

		sizes=new SizesDB(connection);

		toppings=new ToppingsDB(connection);
	}

	public int createNewOrder(
		Date orderDate, Date orderArrived, String PLZ, String street, String houseNumber, String city,
		int sizeID,
		int amount, int toppingsID)
	{
		Orders orders =new Orders(orderDate,orderArrived,PLZ,street,houseNumber,city);
		int orderID= this.orders.insertNewOrder(orders);

		int pizzaID=pizzas.createPizzaEntry(new Pizzas(sizeID));

		PizzaOrder pizzaOrderDTO=new PizzaOrder(orderID,pizzaID);
		pizzaOrder.createEntry(pizzaOrderDTO);

		pizzaTopping.createPizzaToppingsEntry(new PizzaOrder.PizzaTopping(pizzaID,orderID,amount));


		return orderID;
	}

	public void createNewPizzaForExistingOrder(int orderID,
											   int sizeID,
											   int amount, int toppingsID)
	{
		int pizzaID=pizzas.createPizzaEntry(new Pizzas(sizeID));

		PizzaOrder pizzaOrderDTO=new PizzaOrder(orderID,pizzaID);
		pizzaOrder.createEntry(pizzaOrderDTO);

		pizzaTopping.createPizzaToppingsEntry(new PizzaOrder.PizzaTopping(pizzaID,orderID,amount));
	}
}
