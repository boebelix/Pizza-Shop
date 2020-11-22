package Model;

import java.sql.*;

public class PizzaToppingDB {

	private Connection connection;

	public PizzaToppingDB(Connection con) {
		connection=con;
	}

	public void createPizzaToppingsEntry(PizzaToppingDTO DTO)
	{
		try (PreparedStatement statement=connection.prepareStatement("insert into pizza_toppimg (pizza_id, topping_id, amount) VALUES (?,?,?)")){
			statement.setInt(1,DTO.getPizzaId());
			statement.setInt(2,DTO.getToppingId());
			statement.setInt(2,DTO.getAmount());
			statement.executeUpdate();
		}catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}
	}

	public PizzaToppingDTO getPizzaToppingByPizzaId(int Id)
	{
		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from pizza_topping where pizza_id equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new PizzaToppingDTO(rs.getInt(1),
				rs.getInt(2),
				rs.getInt(3));
		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}

	}
	public PizzasDTO getPizzaToppingByToppingId(int Id)
	{

		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from pizzas where size_id equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new PizzasDTO(rs.getInt(1),
				rs.getInt(2));
		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}
	}
}
