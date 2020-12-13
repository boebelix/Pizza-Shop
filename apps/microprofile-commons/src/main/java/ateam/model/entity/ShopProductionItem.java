package ateam.model.entity;

import ateam.validator.Validator;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ShopProductionItem {

	@Validator.Required
	int userID;

	@Validator.Required
	int orderId;

	@Validator.Required
	@Validator.Valid
	@Validator.Min(1)
	List<Pizza> items;

	public ShopProductionItem(int userID, int orderId, List<Pizza> items) {
		this.userID = userID;
		this.orderId = orderId;
		this.items = items;
	}

	public ShopProductionItem() {
		items=new LinkedList<>();
	}

	public int getuserID() {
		return userID;
	}

	public void setuserID(int userID) {
		this.userID = userID;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public List<Pizza> getItems() {
		return items;
	}

	public void setItems(List<Pizza> items) {
		this.items = items;
	}

	public void addPizza(Pizza pizza)
	{
		items.add(pizza);
	}

	public Pizza getPizza(int i)
	{
		return items.get(i);
	}
}
