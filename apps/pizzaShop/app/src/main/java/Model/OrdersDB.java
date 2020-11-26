package Model;

import ateam.model.entity.Orders;

import javax.persistence.EntityManager;
import java.sql.*;

public class OrdersDB {
	private EntityManager em;

	Connection connection;

	public OrdersDB(Connection con) {
		connection=con;
	}

	public int insertNewOrder(Orders orders)
	{
		try (PreparedStatement statement=connection.prepareStatement("insert into orders (id, orderDate, OrderArrived, postalCode, street, houseNumber, city) VALUES (?,?,?,?,?,?)")){
			statement.setDate(1, orders.getOrderDate());
			statement.setDate(2, orders.getOrderArrived());
			statement.setString(3, orders.getPLZ());
			statement.setString(4, orders.getStreet());
			statement.setString(5, orders.getHouseNumber());
			statement.setString(6, orders.getCity());
			statement.executeUpdate();

			//returns OrderID
			return statement.getGeneratedKeys().getInt(1);
		}catch(Exception e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}
		return -1;
	}

	public Orders getOrderById(int Id)
	{

		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from orders where id equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new Orders(rs.getInt(1),
				rs.getDate(2),
				rs.getDate(3),
				rs.getString(4),
				rs.getString(5),
				rs.getString(6),
				rs.getString(7));
		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}

	}

}
