package ateam.shop.db;

import ateam.model.entity.Topping;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
public class ToppingsDB {

	public int createToppingsEntry(Topping dto, Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into toppings (name, base_amount, unit) VALUES (?,?,?)");
		statement.setString(1, dto.getName());
		statement.setInt(2, dto.getBaseAmount());
		statement.setString(3, dto.getUnit());
		statement.execute();
		ResultSet rs = statement.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
	}

	public Topping getToppingById(int id, Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("select * from toppings where id = ?");
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		return new Topping(rs.getInt(1),
			rs.getString(2), rs.getInt(3), rs.getString(4));
	}

}
