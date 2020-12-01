package Model;

import ateam.DBConnection.DBConnector;
import ateam.model.entity.PizzaTopping;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class PizzaToppingDB {

	@Inject
	private DBConnector connector;

	public PizzaToppingDB() {
	}

	public void createPizzaToppingEntry(PizzaTopping dto) throws SQLException {
		try(Connection connection=connector.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("insert into pizza_topping (pizza_id, topping_id, amount) VALUES (?,?,?)");
			statement.setInt(1, dto.getPizzaId());
			statement.setInt(2, dto.getToppingId());
			statement.setInt(2, dto.getAmount());
			statement.executeUpdate();
		}
	}

	public List<PizzaTopping> getPizzaToppingByPizzaId(int pizzaId) throws SQLException {

		try(Connection connection=connector.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("select * from pizza_topping where pizza_id equals ?");
			stmt.setInt(1,pizzaId);

			ResultSet rs = stmt.executeQuery();
			List<PizzaTopping> toppingList = new LinkedList<PizzaTopping>();

			while (rs.next()) {
				toppingList.add(new PizzaTopping(rs.getInt("pizza_order_id"),
					rs.getInt("topping_id"),
					rs.getInt("amount")));
			}

			return toppingList;
		}
	}

}
