package ateam.shop.db;

import ateam.model.entity.PizzaTopping;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class PizzaToppingDB {

	public void createPizzaToppingEntry(PizzaTopping dto, Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into pizza_topping (pizza_id, topping_id, amount) VALUES (?,?,?)");
		statement.setInt(1, dto.getPizzaId());
		statement.setInt(2, dto.getToppingId());
		statement.setInt(2, dto.getAmount());
		statement.execute();
	}

	public List<PizzaTopping> getPizzaToppingByPizzaId(int pizzaId, Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("select * from pizza_topping where pizza_id = ?");
		stmt.setInt(1,pizzaId);

		ResultSet rs = stmt.executeQuery();
		List<PizzaTopping> toppingList = new ArrayList<>();

		while (rs.next()) {
			toppingList.add(pizzaToppingFromRs(rs));
		}
		return toppingList;
	}

	private PizzaTopping pizzaToppingFromRs(ResultSet rs) throws SQLException {
		return new PizzaTopping(rs.getInt("pizza_order_id"),
			rs.getInt("topping_id"),
			rs.getInt("amount"));
	}
}
