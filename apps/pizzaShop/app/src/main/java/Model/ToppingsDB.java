package Model;

import ateam.model.entity.Toppings;

import java.sql.*;

public class ToppingsDB {
	private final Connection connection;

	public ToppingsDB(Connection con) {
		connection = con;
	}

	public void createToppingsEntry(Toppings DTO) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into toppings ( name) VALUES (?)");
		statement.setString(2, DTO.getName());
		statement.executeUpdate();

	}

	public Toppings getToppingById(int Id) throws SQLException {
		Statement stmt = connection.createStatement();

		String Querry = "select * from toppings where id equals " + Id;

		ResultSet rs = stmt.executeQuery(Querry);

		return new Toppings(rs.getInt(1),
			rs.getString(2));

	}

}
