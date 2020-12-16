package ateam.shop.service;

import ateam.model.entity.Order;
import ateam.shop.db.DBManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ShopService {

	@Inject
	private DBManager dbManager;

	public Order placeOrder(Order order) {
		return dbManager.placeOrder(order);
	}
}
