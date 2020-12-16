package ateam.shop.db;

import ateam.db.DBConnection;
import ateam.model.entity.Order;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
public class OrdersDB {

	@Inject
	private DBConnection connector;

	public int insertNewOrder(Order order, Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into orders (id, order_date, order_sent, postal_code, street, hnumber, city) VALUES (?,?,?,?,?,?)");
		statement.setDate(1, order.getOrderDate());
		statement.setDate(2, order.getOrderArrived());
		statement.setString(3, order.getPLZ());
		statement.setString(4, order.getStreet());
		statement.setString(5, order.getHouseNumber());
		statement.setString(6, order.getCity());
		statement.executeUpdate();

		return statement.getGeneratedKeys().getInt(1);
	}

	public Order getOrderById(int id, Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("select * from orders where id = ?");

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			return orderFromRs(rs);
		}
		return null;
	}

	private Order orderFromRs(ResultSet rs) throws SQLException {
		return new Order(rs.getInt(1),
			rs.getDate(2),
			rs.getDate(3),
			rs.getString(4),
			rs.getString(5),
			rs.getString(6),
			rs.getString(7));
	}

}
