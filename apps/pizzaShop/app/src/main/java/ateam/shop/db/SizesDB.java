package ateam.shop.db;

import ateam.db.DBConnection;
import ateam.model.entity.Size;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;

@Singleton
public class SizesDB {

	@Inject
	private DBConnection connector;

	public void createSizeEntry(Size dto) throws SQLException {
		try(Connection connection=connector.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("insert into sizes ( radius, base_price, topping_price) VALUES (?,?,?)");
			statement.setInt(1, dto.getRadius());
			statement.setFloat(2, dto.getBasePrice());
			statement.setFloat(3, dto.getTopping_price());
			statement.executeUpdate();
		}
	}

	public Size getSizesById(int id) throws SQLException {

		try(Connection connection=connector.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("select * from sizes where id = ?");
			stmt.setInt(1,id);

			ResultSet rs = stmt.executeQuery();

			return new Size(rs.getInt(1),
				rs.getInt(2),
				rs.getFloat(3),
				rs.getFloat(4));
		}
	}

}
