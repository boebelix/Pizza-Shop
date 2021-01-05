package ateam.shop.service;

import ateam.model.entity.Order;
import ateam.shop.db.DBManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Date;
import java.util.GregorianCalendar;

@Singleton
public class ShopService {

	@Inject
	private DBManager dbManager;

	public Order placeOrder(Order order) {
		order.setOrderDate(new Date(new GregorianCalendar().getTime().getTime()));
		Order created = dbManager.placeOrder(order);
		//Todo contact pizzaProduction
		return created;
	}
}
