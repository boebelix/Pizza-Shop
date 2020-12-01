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

	public void createToppingsEntry(Toppings dto) throws SQLException {
		try(Connection connection=connector.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("insert into toppings ( name) VALUES (?)");
			statement.setString(1, dto.getName());
			statement.executeUpdate();
		}
	}

	public Toppings getToppingById(int id) throws SQLException {

		try(Connection connection=connector.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("select * from toppings where id equals ?");
			stmt.setInt(1,id);

			ResultSet rs = stmt.executeQuery();

			return new Toppings(rs.getInt(1),
				rs.getString(2));
		}
	}

}
