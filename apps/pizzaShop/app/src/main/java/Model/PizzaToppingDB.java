package Model;

import ateam.model.entity.PizzaTopping;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PizzaToppingDB {

	private Connection connection;

	public PizzaToppingDB(Connection con) {
		connection=con;
	}

	public void createPizzaToppingsEntry(PizzaTopping DTO)
	{
		try (PreparedStatement statement=connection.prepareStatement("select pizza_id,topping_id from pizza_topping where pizza_id=? and topping_id=?")){
			statement.setInt(1,DTO.getPizzaId());
			statement.setInt(2,DTO.getToppingId());

			ResultSet r =statement.executeQuery();

			if(r.next())
				return;

		}catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}
		try (PreparedStatement statement=connection.prepareStatement("insert into pizza_topping (pizza_id, topping_id, amount) VALUES (?,?,?)")){
			statement.setInt(1,DTO.getPizzaId());
			statement.setInt(2,DTO.getToppingId());
			statement.setInt(2,DTO.getAmount());
			statement.executeUpdate();
		}catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}
	}

	public List<PizzaTopping> getPizzaToppingByPizzaId(int Id)
	{
		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from pizza_topping where pizza_id equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);
			List<PizzaTopping> ToppingList=new LinkedList<PizzaTopping>();

			while(rs.next())
			{
				ToppingList.add(new PizzaTopping(rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3)));
			}

			return ToppingList;

		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}

	}
	public List<Integer> getToppingByPizzaId(int Id)
	{

		try {
			Statement stmt = connection.createStatement();

			String Querry = "select pizza_id from pizza_topping where size_id equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);
			List<Integer> ToppingList=new LinkedList<Integer>();

			while(rs.next())
			{
				ToppingList.add(rs.getInt(1));
			}

			return ToppingList;
		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}
	}
}
