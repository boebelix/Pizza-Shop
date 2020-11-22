package Model;
import java.sql.*;

public class PizzaOrderDB {
	Connection connection;

	public PizzaOrderDB(Connection con) {
		connection=con;
	}

	public void createEntry(PizzaOrderDTO DTO)
	{

		try (PreparedStatement statement=connection.prepareStatement("insert into pizzaOrder (pizzaID, orderId) VALUES (?,??,?,?)")){
			statement.setInt(1,DTO.getOrderId());
			statement.setInt(2,DTO.getPizzaID());
			statement.executeUpdate();
		}catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}
	}

	public PizzaOrderDTO getOrderByPizzaId(int Id)
	{
		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from pizzaOrder where pizzaID equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new PizzaOrderDTO(rs.getInt(1),
				rs.getInt(2));
		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}

	}
}
