package Model;

import ateam.model.entity.PizzaOrderTopping;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PizzaToppingOrderDB {

	private Connection connection;

	public PizzaToppingOrderDB(Connection con) {
		connection=con;
	}

	public void createPizzaToppingsEntry(PizzaOrderTopping DTO)
	{
		try (PreparedStatement statement=connection.prepareStatement("select pizza_order_id,topping_id, amount from pizza_order_topping where pizza_order_id=? and topping_id=?")){
			statement.setInt(1,DTO.getPizzaOrderId());
			statement.setInt(2,DTO.getToppingId());

			ResultSet r =statement.executeQuery();

			if(r.next())
				return;

		}catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}
		try (PreparedStatement statement=connection.prepareStatement("insert into pizza_order_topping (pizza_order_id, topping_id, amount) VALUES (?,?,?)")){
			statement.setInt(1,DTO.getPizzaOrderId());
			statement.setInt(2,DTO.getToppingId());
			statement.setInt(2,DTO.getAmount());
			statement.executeUpdate();
		}catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}
	}

	public List<PizzaOrderTopping> getPizzaToppingByPizzaOrderId(int Id)
	{
		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from pizza_order_topping where pizza_order_id equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);
			List<PizzaOrderTopping> ToppingList=new LinkedList<PizzaOrderTopping>();

			while(rs.next())
			{
				ToppingList.add(new PizzaOrderTopping(rs.getInt(1),
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

}
