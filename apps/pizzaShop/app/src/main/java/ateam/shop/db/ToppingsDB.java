package ateam.shop.db;

import ateam.db.DBConnection;
import ateam.model.entity.Topping;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
public class ToppingsDB {

	@Inject
	private DBConnection connector;

	public void createToppingsEntry(Topping dto) throws SQLException {
		try(Connection connection=connector.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("insert into toppings (name, base_amount, unit) VALUES (?,?,?)");
			statement.setString(1, dto.getName());
			statement.setInt(2, dto.getBaseAmount());
			statement.setString(3, dto.getUnit());
			statement.executeUpdate();
		}
	}

	public Topping getToppingById(int id) throws SQLException {

		try(Connection connection=connector.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("select * from toppings where id = ?");
			stmt.setInt(1,id);

			ResultSet rs = stmt.executeQuery();

			return new Topping(rs.getInt(1),
				rs.getString(2), rs.getInt(3), rs.getString(4));
		}
	}

}
