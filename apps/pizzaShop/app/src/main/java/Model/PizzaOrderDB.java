package Model;
import ateam.model.entity.PizzaOrder;

import java.sql.*;

public class PizzaOrderDB {
	Connection connection;

	public PizzaOrderDB(Connection con) {
		connection=con;
	}

	public void createEntry(PizzaOrder DTO)
	{

		try (PreparedStatement statement=connection.prepareStatement("insert into pizza_order (pizzaID, orderId) VALUES (?,??,?,?)")){
			statement.setInt(1,DTO.getOrderId());
			statement.setInt(2,DTO.getPizzaID());
			statement.executeUpdate();
		}catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}
	}

	public PizzaOrder getOrderByPizzaId(int Id)
	{
		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from pizzaOrder where pizzaID equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new PizzaOrder(rs.getInt(1),
				rs.getInt(2));
		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}

	}
}
