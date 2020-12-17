package ateam.shop.db;

import ateam.db.DBConnection;
import ateam.model.entity.Order;
import ateam.model.exception.PizzaShopException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;

@Singleton
public class OrdersDB {

	@Inject
	private DBConnection connector;

	public int insertNewOrder(Order order, Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into orders (order_date, order_sent, postal_code, street, number, city, country) VALUES (?,?,?,?,?,?,?)",
			Statement.RETURN_GENERATED_KEYS);
		statement.setDate(1, order.getOrderDate());
		statement.setDate(2, order.getOrderArrived());
		statement.setString(3, order.getPostCode());
		statement.setString(4, order.getStreet());
		statement.setString(5, order.getHouseNumber());
		statement.setString(6, order.getCity());
		statement.setString(7, order.getCountry());
		statement.execute();

		ResultSet genKeys = statement.getGeneratedKeys();
		while (genKeys.next()) {
			return genKeys.getInt(1);
		}
		throw new PizzaShopException("Couldn't get order keys!");
	}

	public Order getOrderById(int id, Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("select * from orders where id = ?");
		stmt.setInt(1, id);

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
			rs.getString(7),
			rs.getString(8));
	}

}
