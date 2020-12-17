package ateam.shop.db;

import ateam.model.entity.Size;

import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class SizesDB {

	public int createSizeEntry(Size dto, Connection connection) throws SQLException {
		PreparedStatement statement = connection
			.prepareStatement("insert into sizes ( radius, base_price, topping_price, dough_amount, topping_factor) VALUES (?,?,?,?,?)");
		statement.setInt(1, dto.getRadius());
		statement.setFloat(2, dto.getBasePrice());
		statement.setFloat(3, dto.getToppingPrice());
		statement.setInt(4, dto.getDoughAmount());
		statement.setFloat(5, dto.getToppingFactor());
		statement.execute();
		ResultSet rs = statement.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
	}

	public List<Size> getSizes(Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("select * from sizes");
		ResultSet rs = stmt.executeQuery();
		List<Size> sizes = new ArrayList<>();
		while (rs.next()) {
			sizes.add(sizeFromRs(rs));
		}
		return sizes;
	}

	public Size getSizesById(int id, Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("select * from sizes where id = ?");
		stmt.setInt(1,id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			return sizeFromRs(rs);
		}
		return null;
	}

	private Size sizeFromRs(ResultSet rs) throws SQLException {
		return new Size(rs.getInt("id"),
			rs.getInt("radius"),
			rs.getFloat("base_price"),
			rs.getFloat("topping_price"),
			rs.getInt("dough_amount"),
			rs.getFloat("topping_factor"));
	}

}
