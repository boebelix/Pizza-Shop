package ateam.model.entity;

import ateam.validator.Validator;

import java.util.LinkedList;
import java.util.List;

public class ShopProductionItem {

	@Validator.Required
	int userID;

	@Validator.Required
	int orderID;

	@Validator.Required
	@Validator.Valid
	@Validator.Min(1)
	List<PizzaRestEntity> items;

	public ShopProductionItem(int userID, int orderId, List<PizzaRestEntity> items) {
		this.userID = userID;
		this.orderID = orderId;
		this.items = items;
	}

	public ShopProductionItem() {
		items=new LinkedList<>();
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderId) {
		this.orderID = orderId;
	}

	public List<PizzaRestEntity> getItems() {
		return items;
	}

	public void setItems(List<PizzaRestEntity> items) {
		this.items = items;
	}

	public void addPizza(PizzaRestEntity pizza)
	{
		items.add(pizza);
	}

	public PizzaRestEntity getPizza(int i)
	{
		return items.get(i);
	}

	@Override
	public String toString()
	{
		String data="ShopProductionItem{";

		data+="userID="+userID;
		data+="orderID="+orderID;
		for (PizzaRestEntity item:items)
		{
			data+=item.toString();
		}
		return data+"}";
	}
}
