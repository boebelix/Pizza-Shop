package Model;

import ateam.model.entity.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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

		pizzaTopping.createPizzaToppingsEntry(new PizzaTopping(pizzaID,orderID,amount));


		return orderID;
	}

	public void createNewPizzaForExistingOrder(int orderID,
											   int sizeID,
											   int amount, int toppingsID)
	{
		int pizzaID=pizzas.createPizzaEntry(new Pizzas(sizeID));

		PizzaOrder pizzaOrderDTO=new PizzaOrder(orderID,pizzaID);
		pizzaOrder.createEntry(pizzaOrderDTO);

		pizzaTopping.createPizzaToppingsEntry(new PizzaTopping(pizzaID,orderID,amount));
	}
	public void placeOrder(Orders order)
	{
		int orderID= this.orders.insertNewOrder(order);

		for(Pizzas pizza :order.getPizzas()) {

			int pizzaID = pizzas.createPizzaEntry(pizza);

			PizzaOrder pizzaOrderDTO=new PizzaOrder(orderID,pizzaID);
			pizzaOrder.createEntry(pizzaOrderDTO);

			pizzaTopping.createPizzaToppingsEntry(new PizzaTopping(pizzaID,orderID,1));
		}

	}

	public Orders createOrderObjectFromDB(int OrderId){

		Orders order=orders.getOrderById(OrderId);

		for(int id:pizzaOrder.getPizzasByOrderId(OrderId))
		{
			Pizzas pizza = pizzas.getPizzaById(id);

			List<Toppings> toppingsList = new LinkedList<Toppings>();

			for (int toppingId : pizzaTopping.getToppingByPizzaId(id))
			{
				pizza.addTopping(toppings.getToppingById(toppingId));
			}

			order.addPizza(pizza);
		}

		return order;

	}
}
