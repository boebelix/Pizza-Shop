package ateam.shop.db;

import ateam.DBConnection.DBConnector;
import ateam.model.entity.Order;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;

@Singleton
public class OrdersDB {

	@Inject
	private DBConnector connector;

	public int insertNewOrder(Order orders) throws SQLException {

		try(Connection connection=connector.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("insert into orders (id, order_date, order_sent, postal_code, street, hnumber, city) VALUES (?,?,?,?,?,?)");
			statement.setDate(1, orders.getOrderDate());
			statement.setDate(2, orders.getOrderArrived());
			statement.setString(3, orders.getPLZ());
			statement.setString(4, orders.getStreet());
			statement.setString(5, orders.getHouseNumber());
			statement.setString(6, orders.getCity());
			statement.executeUpdate();

			return statement.getGeneratedKeys().getInt(1);
		}
	}

	public Order getOrderById(int id) throws SQLException {

		try(Connection connection=connector.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("select * from orders where id equals ?");

			ResultSet rs = stmt.executeQuery();

			return new Order(rs.getInt(1),
				rs.getDate(2),
				rs.getDate(3),
				rs.getString(4),
				rs.getString(5),
				rs.getString(6),
				rs.getString(7));
		}
	}

}
