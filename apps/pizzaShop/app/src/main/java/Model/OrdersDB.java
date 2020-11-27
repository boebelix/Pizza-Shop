package Model;

import ateam.model.entity.Orders;

import javax.persistence.EntityManager;
import java.sql.*;

public class OrdersDB {
	Connection connection;
	private EntityManager em;

	public OrdersDB(Connection con) {
		connection = con;
	}

	public int insertNewOrder(Orders orders) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into orders (id, order_date, order_sent, postal_code, street, hnumber, city) VALUES (?,?,?,?,?,?)");
		statement.setDate(1, orders.getOrderDate());
		statement.setDate(2, orders.getOrderArrived());
		statement.setString(3, orders.getPLZ());
		statement.setString(4, orders.getStreet());
		statement.setString(5, orders.getHouseNumber());
		statement.setString(6, orders.getCity());
		statement.executeUpdate();

		//returns OrderID
		return statement.getGeneratedKeys().getInt(1);

	}

	public Orders getOrderById(int Id) throws SQLException {
		Statement stmt = connection.createStatement();

		String Querry = "select * from orders where id equals " + Id;

		ResultSet rs = stmt.executeQuery(Querry);

		return new Orders(rs.getInt(1),
			rs.getDate(2),
			rs.getDate(3),
			rs.getString(4),
			rs.getString(5),
			rs.getString(6),
			rs.getString(7));
	}

}
