package Model;

import ateam.model.entity.Sizes;

import java.sql.*;

public class SizesDB {

	private Connection connection;

	public SizesDB(Connection con) {
		connection=con;
	}

	public void createSizeEntry(Sizes DTO) throws SQLException {
		PreparedStatement statement=connection.prepareStatement("insert into sizes ( radius, base_price, topping_price) VALUES (?,?,?)");
		statement.setInt(1,DTO.getRadius());
		statement.setFloat(2,DTO.getBasePrice());
		statement.setFloat(3,DTO.getTopping_price());
		statement.executeUpdate();
	}

	public Sizes getSizesById(int Id) throws SQLException {

			Statement stmt = connection.createStatement();

			String Querry = "select * from sizes where id = "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new Sizes(rs.getInt(1),
				rs.getInt(2),
				rs.getFloat(3),
				rs.getFloat(4));

	}

}
