package ateam.shop.db;

import ateam.model.entity.Pizza;
import ateam.model.exception.PizzaShopException;

import javax.inject.Singleton;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class PizzasDB {

	public int createPizza(Pizza dto, Connection connection) throws SQLException {

		PreparedStatement statement = connection.prepareStatement("insert into pizzas (order_id,size_id) VALUES (?,?)",
			Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, dto.getOrderId());
		statement.setInt(2, dto.getSizeId());
		statement.execute();

		ResultSet genKeys = statement.getGeneratedKeys();
		while (genKeys.next()) {
			return genKeys.getInt(1);
		}
		throw new PizzaShopException("Couldn't get pizza keys!");
	}

	public Pizza getPizzaById(int id, Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("select * from pizzas where id = ?");
		stmt.setInt(1, id);
		stmt.execute();
		ResultSet rs = stmt.getResultSet();
		while (rs.next()) {
			return pizzaFromRs(rs);
		}
		return null;
	}

	public List<Pizza> getPizzasByOrderId(int orderId, Connection connection) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("select * from pizzas where order_id = ?");
		stmt.setInt(1, orderId);
		ResultSet rs = stmt.executeQuery();
		List<Pizza> pizzen = new LinkedList<>();
		while (rs.next()) {
			pizzen.add(pizzaFromRs(rs));
		}
		return pizzen;
	}

	private Pizza pizzaFromRs(ResultSet rs) throws SQLException {
		Pizza pizza = new Pizza();
		pizza.setId(rs.getInt("id"));
		pizza.setOrderId(rs.getInt("order_id"));
		pizza.setSizeId(rs.getInt("size_id"));
		return pizza;
	}
}
