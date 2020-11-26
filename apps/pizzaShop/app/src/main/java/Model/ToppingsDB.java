package Model;

import ateam.model.entity.Toppings;

import java.sql.*;

public class ToppingsDB {
	private Connection connection;

	public ToppingsDB(Connection con) {
		connection=con;
	}

	public void createOrderEntry(Toppings DTO)
	{
		try (PreparedStatement statement=connection.prepareStatement("insert into toppings ( name) VALUES (?)")){
			statement.setString(2,DTO.getName());
			statement.executeUpdate();
		}catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}
	}

	public Toppings getOrderById(int Id)
	{
		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from toppings where id equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new Toppings(rs.getInt(1),
				rs.getString(2));
		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}
	}

}
