package ateam.shop.service;

import ateam.model.entity.Order;
import ateam.shop.db.OrdersDB;
import ateam.shop.db.PizzaToppingDB;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ShopService {

	@Inject
	private OrdersDB ordersDB;

	@Inject
	private PizzaToppingDB pizzaToppingDB;


	public Order placeOrder(Order order) {
		//Check foreign keys. Pizza Check if pizza id is needed
		return ordersDB.insertNewOrder(order);
	}
}
