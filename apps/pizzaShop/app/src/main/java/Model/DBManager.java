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
	PizzaToppingOrderDB pizzaTopping;
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

		pizzaTopping=new PizzaToppingOrderDB(connection);

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

		pizzaTopping.createPizzaToppingsEntry(new PizzaOrderTopping(pizzaID,orderID,amount));


		return orderID;
	}

	public void createNewPizzaForExistingOrder(int orderID,
											   int sizeID,
											   int amount, int toppingsID)
	{
		int pizzaID=pizzas.createPizzaEntry(new Pizzas(sizeID));

		PizzaOrder pizzaOrderDTO=new PizzaOrder(orderID,pizzaID);
		int pizzaOrderId=pizzaOrder.createEntry(pizzaOrderDTO);

		pizzaTopping.createPizzaToppingsEntry(new PizzaOrderTopping(pizzaOrderId,orderID,amount));
	}
	public void placeOrder(Orders order)
	{
		int orderID= this.orders.insertNewOrder(order);

		for(Pizzas pizza :order.getPizzas()) {

			int pizzaID = pizzas.createPizzaEntry(pizza);

			PizzaOrder pizzaOrderDTO=new PizzaOrder(orderID,pizzaID);
			int pizzaOrderId=pizzaOrder.createEntry(pizzaOrderDTO);

			for(Toppings t:pizza.getToppings())
				pizzaTopping.createPizzaToppingsEntry(new PizzaOrderTopping(pizzaOrderId,t.getId(),1));//1 ist ein Dummy Value, ich sah in der API nicht, dass die Menge übertragen wird
		}

	}

	public Orders createOrderObjectFromDB(int OrderId){

		Orders order=orders.getOrderById(OrderId);

		for(int id:pizzaOrder.getPizzasByOrderId(OrderId))
		{
			Pizzas pizza = pizzas.getPizzaById(id);

			List<Toppings> toppingsList = new LinkedList<Toppings>();

			List<PizzaOrderTopping> connectionList= pizzaTopping.getPizzaToppingByPizzaOrderId(OrderId);

			for (PizzaOrderTopping toppingId : connectionList)
			{
				pizza.addTopping(toppings.getToppingById(toppingId.getToppingId()));
			}

			order.addPizza(pizza);
		}

		return order;

	}
}
