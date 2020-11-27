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

	public void createPizzaToppingOrderEntry(PizzaOrderTopping DTO) throws SQLException {

		PreparedStatement stmnt=connection.prepareStatement("select pizza_order_id,topping_id, amount from pizza_order_topping where pizza_order_id=? and topping_id=?");
		stmnt.setInt(1,DTO.getPizzaOrderId());
		stmnt.setInt(2,DTO.getToppingId());

		ResultSet r =stmnt.executeQuery();

		if(r.next())
			return;

		PreparedStatement statement=connection.prepareStatement("insert into pizza_order_topping (pizza_order_id, topping_id, amount) VALUES (?,?,?)");
			statement.setInt(1,DTO.getPizzaOrderId());
			statement.setInt(2,DTO.getToppingId());
			statement.setInt(2,DTO.getAmount());
			statement.executeUpdate();
	}

	public List<PizzaOrderTopping> getPizzaToppingByPizzaOrderId(int Id) throws SQLException
	{
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

}
