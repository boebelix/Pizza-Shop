package ateam.shop.db;

import ateam.db.DBConnection;
import ateam.model.entity.Pizza;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class PizzasDB {

	@Inject
	private DBConnection connector;

	public int createPizzaEntry(Pizza dto) throws SQLException {
		try(Connection connection=connector.getConnection()) {
			PreparedStatement statement = connection.prepareStatement("insert into pizzas ( order_id,size_id) VALUES (?,?)");
			statement.setInt(1, dto.getOrderId());
			statement.setInt(2, dto.getSizeId());
			statement.executeUpdate();

			return statement.getGeneratedKeys().getInt(1);
		}
	}

	public List<Pizza> getPizzaByOrderId(int orderId) throws SQLException {

		try(Connection connection=connector.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement("select * from pizzas where order_id equals ?");
			stmt.setInt(1,orderId);

			ResultSet rs = stmt.executeQuery();

			List<Pizza> pizzen = new LinkedList<>();

			while (rs.next()) {
				pizzen.add(new Pizza(rs.getInt("id"),
					rs.getInt("size_id"),
					rs.getInt("order_id")));
			}

			return pizzen;
		}
	}

	public Pizza getPizzaBySizeId(int id) throws SQLException {

		try(Connection connection=connector.getConnection()) {

			PreparedStatement stmt = connection.prepareStatement("select * from pizzas where order_id equals ?");
			stmt.setInt(1,id);

			ResultSet rs = stmt.executeQuery();

			return new Pizza(rs.getInt("id"),
				rs.getInt("size_id"),
				rs.getInt("order_id"));
		}
	}
}
