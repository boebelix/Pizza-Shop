package Model;

import ateam.DBConnection.DBConnector;
import ateam.model.entity.Sizes;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;

@Singleton
public class SizesDB {

	@Inject
	private DBConnector connector;

	public SizesDB() {

	}

	public void createSizeEntry(Sizes DTO) throws SQLException {
		try(Connection connection=connector.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("insert into sizes ( radius, base_price, topping_price) VALUES (?,?,?)");
			statement.setInt(1, DTO.getRadius());
			statement.setFloat(2, DTO.getBasePrice());
			statement.setFloat(3, DTO.getTopping_price());
			statement.executeUpdate();
		}
	}

	public Sizes getSizesById(int Id) throws SQLException {

		try(Connection connection=connector.getConnection()) {
			Statement stmt = connection.createStatement();

			String Querry = "select * from sizes where id = " + Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new Sizes(rs.getInt(1),
				rs.getInt(2),
				rs.getFloat(3),
				rs.getFloat(4));
		}
	}

}
