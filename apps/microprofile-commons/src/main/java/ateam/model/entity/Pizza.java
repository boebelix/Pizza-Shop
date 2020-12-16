package ateam.model.entity;

import ateam.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class Pizza {

	@Validator.Required
	@Validator.Valid
	List<PizzaTopping> toppings;

	private int iD;

	@Validator.Required
	private int sizeId;

	@Validator.Required
	private int orderId;

	public Pizza() {
		toppings = new ArrayList<>();
	}

	public Pizza(int iD, int sizeId, int orderId) {
		this.iD = iD;
		this.sizeId = sizeId;
		this.orderId = orderId;
		toppings = new ArrayList<>();
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

	public List<PizzaTopping> getToppings() {
		return toppings;
	}

	public void setToppings(List<PizzaTopping> toppings) {
		this.toppings = toppings;
	}

	public void addTopping(PizzaTopping topping) {
		toppings.add(topping);
	}
}
