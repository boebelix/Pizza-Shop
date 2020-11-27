package Model;

import ateam.model.entity.PizzaOrder;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PizzaOrderDB {
	Connection connection;

	public PizzaOrderDB(Connection con) {
		connection=con;
	}

	public int createEntry(PizzaOrder DTO)
	{

		try (PreparedStatement statement=connection.prepareStatement("insert into pizza_order (order_id, pizza_id) VALUES (?,?,)")){
			statement.setInt(1,DTO.getOrderId());
			statement.setInt(2,DTO.getPizzaID());
			ResultSet rs=statement.executeQuery();

			statement.executeUpdate();

			//returns OrderID
			return statement.getGeneratedKeys().getInt(1);

		}catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}
		return 0;
	}

	public List<Integer> getPizzasByOrderId(int Id)
	{
		try {
			Statement stmt = connection.createStatement();

			String Querry = "select pizzaID from pizzaOrder where orderID equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			List<Integer> pizzaOrderList=new LinkedList<Integer>();

			while(rs.next())
			{
				pizzaOrderList.add(rs.getInt(1));
			}

			return pizzaOrderList;
		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}

	}
}
