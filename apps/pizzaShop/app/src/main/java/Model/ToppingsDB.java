package Model;

import ateam.DBConnection.DBConnector;
import ateam.model.entity.Toppings;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;

@Singleton
public class ToppingsDB {

	@Inject
	private DBConnector connector;

	public ToppingsDB() {

	}

	public void createToppingsEntry(Toppings DTO) throws SQLException {
		try(Connection connection=connector.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("insert into toppings ( name) VALUES (?)");
			statement.setString(2, DTO.getName());
			statement.executeUpdate();
		}
	}

	public Toppings getToppingById(int Id) throws SQLException {

		try(Connection connection=connector.getConnection()) {
			Statement stmt = connection.createStatement();

			String Querry = "select * from toppings where id equals " + Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new Toppings(rs.getInt(1),
				rs.getString(2));
		}
	}

}
