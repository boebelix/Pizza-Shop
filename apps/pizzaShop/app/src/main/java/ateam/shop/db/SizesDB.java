package ateam.shop.db;

import ateam.model.entity.Size;
import ateam.model.exception.PizzaShopException;

import javax.inject.Singleton;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class SizesDB {

	public int createSizeEntry(Size dto, Connection connection) throws SQLException {
		PreparedStatement statement = connection
			.prepareStatement("insert into sizes ( diameter, base_price, topping_price, dough_amount, topping_factor) VALUES (?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, dto.getDiameter());
		statement.setFloat(2, dto.getBasePrice());
		statement.setFloat(3, dto.getToppingPrice());
		statement.setInt(4, dto.getDoughAmount());
		statement.setFloat(5, dto.getToppingFactor());
		statement.execute();

		ResultSet genKeys = statement.getGeneratedKeys();
		while (genKeys.next()) {
			return genKeys.getInt(1);
		}
		throw new PizzaShopException("Couldn't get size keys!");
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
			rs.getInt("diameter"),
			rs.getFloat("base_price"),
			rs.getFloat("topping_price"),
			rs.getInt("dough_amount"),
			rs.getFloat("topping_factor"));
	}

}
