package Model;

import javax.persistence.EntityManager;
import java.sql.*;

public class OrdersDB {
	private EntityManager em;

	Connection connection;

	public OrdersDB(Connection con) {
		connection=con;
	}

	public void insertNewOrder(OrdersDTO ordersDTO)
	{
		try (PreparedStatement statement=connection.prepareStatement("insert into orders (id, orderDate, OrderArrived, postalCode, street, houseNumber, city) VALUES (?,?,?,?,?,?,?)")){
			statement.setInt(1,ordersDTO.getId());
			statement.setDate(2,ordersDTO.getOrderDate());
			statement.setDate(3,ordersDTO.getOrderArrived());
			statement.setString(4,ordersDTO.getPLZ());
			statement.setString(5,ordersDTO.getStreet());
			statement.setString(6,ordersDTO.getHouseNumber());
			statement.setString(7,ordersDTO.getCity());
			statement.executeUpdate();
		}catch(Exception e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}
	}

	public OrdersDTO getOrderById(int Id)
	{

		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from Lieferant where id equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new OrdersDTO(rs.getInt(1),
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
