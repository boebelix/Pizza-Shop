package Model;

import ateam.model.entity.PizzaTopping;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PizzaToppingDB {

	private final Connection connection;

	public PizzaToppingDB(Connection con) {
		connection=con;
	}

	public void createPizzaToppingEntry(PizzaTopping dto) throws SQLException {

		PreparedStatement statement=connection.prepareStatement("insert into pizza_topping (pizza_id, topping_id, amount) VALUES (?,?,?)");
			statement.setInt(1,dto.getPizzaId());
			statement.setInt(2,dto.getToppingId());
			statement.setInt(2,dto.getAmount());
			statement.executeUpdate();
	}

	public List<PizzaTopping> getPizzaToppingByPizzaId(int pizzaId) throws SQLException
	{
		Statement stmt = connection.createStatement();

		String Querry = "select * from pizza_topping where pizza_id equals "+pizzaId;

		ResultSet rs = stmt.executeQuery(Querry);
		List<PizzaTopping> ToppingList=new LinkedList<PizzaTopping>();

		while(rs.next())
		{
			ToppingList.add(new PizzaTopping(rs.getInt("pizza_order_id"),
				rs.getInt("topping_id"),
				rs.getInt("amount")));
		}

		return ToppingList;

	}

}
