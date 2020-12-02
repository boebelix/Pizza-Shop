package ateam.model.entity;

import ateam.validator.Validator;

import java.util.LinkedList;
import java.util.List;

public class Pizza {

	@Validator.Required
	@Validator.Valid
	List<Topping> toppings;

	private int iD;

	@Validator.Required
	private int sizeId;

	@Validator.Required
	private int orderId;

	public Pizza() {
		toppings = new LinkedList<Topping>();
	}

	public Pizza(int iD, int sizeId, int orderId) {
		this.iD = iD;
		this.sizeId = sizeId;
		this.orderId = orderId;
		toppings = new LinkedList<Topping>();
	}

	public Pizza(int sizeId) {
		this.sizeId = sizeId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getID() {
		return iD;
	}

	public void setID(int iD) {
		this.iD = iD;
	}

	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public List<Topping> getToppings() {
		return toppings;
	}

	public void setToppings(List<Topping> toppings) {
		this.toppings = toppings;
	}

	public void addTopping(Topping topping) {
		toppings.add(topping);
	}
}
