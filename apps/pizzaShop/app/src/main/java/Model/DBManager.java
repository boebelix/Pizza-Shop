package Model;

import java.sql.Connection;
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
}
