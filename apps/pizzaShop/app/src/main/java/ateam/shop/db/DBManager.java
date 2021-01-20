package ateam.shop.db;

import ateam.db.DBConnection;
import ateam.model.entity.*;
import ateam.model.exception.ShopException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Singleton
public class DBManager {

	private static final int ERROR_CODE_NOT_EXISTING_FOREIGN_KEY = 1452;

	@Inject
	private DBConnection connector;

	@Inject
	private PizzaToppingDB pizzaToppingDB;

	@Inject
	private OrdersDB ordersDB;

	@Inject
	private PizzasDB pizzasDB;

	@Inject
	private SizesDB sizesDB;

	@Inject
	private ToppingsDB toppingsDB;

	/*
	 *Nimmt Order Objekt und fügt was noch nicht vorhanden ist in Datenbank ein
	 */
	public Order placeOrder(Order order) {
		try (Connection connection = connector.getConnection()) {
			try {
				connection.setAutoCommit(false);
				int orderId = placeOrder(order, connection);
				Order createdOrder = getOrderById(orderId, connection);
				connection.commit();
				return createdOrder;
			} catch (Throwable e) {
				connection.rollback();
				throw e;
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new ShopException("Error inserting order", e);
		}
	}

	public Order getOrderById(int id) {
		try (Connection connection = connector.getConnection()) {
			return getOrderById(id, connection);
		} catch (SQLException e) {
			throw new ShopException("Error loading order", e);
		}
	}

	public List<Topping> getToppings() {
		try (Connection connection = connector.getConnection()) {
			return getToppings(connection);
		} catch (SQLException e) {
			throw new ShopException("Error loading orders", e);
		}
	}

	public Topping getToppingById(int id) {
		try (Connection connection = connector.getConnection()) {
			return getToppingById(id, connection);
		} catch (SQLException e) {
			throw new ShopException("Error loading orders", e);
		}
	}

	private Topping getToppingById(int id, Connection connection) throws SQLException {
		return toppingsDB.getToppingById(id, connection);
	}
	public List<Size> getSizes() {
		try (Connection connection = connector.getConnection()) {
			return getSizes(connection);
		} catch (SQLException e) {
			throw new ShopException("Error loading sizes", e);
		}
	}

	public Size getSizeById(int id) {
		try (Connection connection = connector.getConnection()) {
			return getSizeById(id, connection);
		} catch (SQLException e) {
			throw new ShopException("Error loading sizes", e);
		}
	}

	private List<Size> getSizes(Connection connection) throws SQLException {
		return sizesDB.getSizes(connection);
	}

	private Size getSizeById(int id, Connection connection) throws SQLException {
		return sizesDB.getSizesById(id, connection);
	}

	private List<Topping> getToppings(Connection connection) throws SQLException {
		return toppingsDB.getToppings(connection);
	}

	private int placeOrder(Order order, Connection connection) throws SQLException {
		int orderId = ordersDB.insertNewOrder(order, connection);
		for (Pizza pizza : order.getPizzas()) {
			pizza.setOrderId(orderId);
			int pizzaId;
			try {
				pizzaId = pizzasDB.createPizza(pizza, connection);
			} catch (SQLException e) {
				if(e.getErrorCode() == ERROR_CODE_NOT_EXISTING_FOREIGN_KEY) {
					throw new ShopException("Size with id " + pizza.getSizeId() + " doesn't exist!");
				}
				throw e;
			}

			for (PizzaTopping pizzaTopping : pizza.getToppings()) {
				pizzaTopping.setPizzaId(pizzaId);
				try {
					pizzaToppingDB.createPizzaToppingEntry(pizzaTopping, connection);
				} catch (SQLException e) {
					if(e.getErrorCode() == ERROR_CODE_NOT_EXISTING_FOREIGN_KEY) {
						throw new ShopException("Topping with id " + pizzaTopping.getToppingId() + " doesn't exist!");
					}
					throw e;
				}
			}
		}
		return orderId;
	}

	private Order getOrderById(int id, Connection connection) throws SQLException {
		Order order = ordersDB.getOrderById(id, connection);
		if (order == null) {
			return order;
		}
		order.setPizzas(pizzasDB.getPizzasByOrderId(id, connection));
		for (Pizza pizza : order.getPizzas()) {
			pizza.setToppings(pizzaToppingDB.getPizzaToppingByPizzaId(pizza.getId(), connection));
		}
		return order;
	}
}
