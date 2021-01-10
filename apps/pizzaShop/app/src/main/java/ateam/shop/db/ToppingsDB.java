package ateam.shop.db;

import ateam.model.entity.Topping;
import ateam.model.exception.PizzaShopException;

import javax.inject.Singleton;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ToppingsDB {

	public int createToppingsEntry(Topping dto, Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into toppings (name, base_amount, unit) VALUES (?,?,?)",
			Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, dto.getName());
		statement.setInt(2, dto.getBaseAmount());
		statement.setString(3, dto.getUnit());
		statement.execute();

		ResultSet genKeys = statement.getGeneratedKeys();
		while (genKeys.next()) {
			return genKeys.getInt(1);
		}
		throw new PizzaShopException("Couldn't get size keys!");
	}

	public Topping getToppingById(int id, Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("select * from toppings where id = ?");
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return new Topping(rs.getInt(1),
				rs.getString(2), rs.getInt(3), rs.getString(4));
		}
		return null;
	}

    public List<Topping> getToppings(Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("select * from toppings");
		ResultSet rs = stmt.executeQuery();
		List<Topping> toppings = new ArrayList<>();
		while (rs.next()) {
			toppings.add(new Topping(rs.getInt(1),
				rs.getString(2), rs.getInt(3), rs.getString(4)));
		}
		return toppings;
    }
}
