package Model;

import java.sql.*;

public class SizesDB {

	private Connection connection;

	public SizesDB(Connection con) {
		connection=con;
	}

	public void createOrderEntry(SizesDTO DTO)
	{
		try (PreparedStatement statement=connection.prepareStatement("insert into sizes (id, radius, base_price, topping_price) VALUES (?,?,?,?)")){
			statement.setInt(1,DTO.getId());
			statement.setInt(2,DTO.getRadius());
			statement.setFloat(3,DTO.getBasePrice());
			statement.setFloat(4,DTO.getTopping_price());
			statement.executeUpdate();
		}catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}

	}

	public SizesDTO getSizesById(int Id)
	{
		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from sizes where id equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new SizesDTO(rs.getInt(1),
				rs.getInt(2),
				rs.getFloat(3),
				rs.getFloat(4));
		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}
	}

}
