package ateam.model.entity;

import ateam.validator.Validator;

import java.util.LinkedList;
import java.util.List;

public class Pizzas {

	@Validator.Required
	List<Toppings> toppings;

	private int iD;

	@Validator.Required
	private int sizeId;

	@Validator.Required
	private int orderId;

	public Pizzas() {
		toppings = new LinkedList<Toppings>();
	}

	public Pizzas(int iD, int sizeId, int orderId) {
		this.iD = iD;
		this.sizeId = sizeId;
		this.orderId = orderId;
		toppings = new LinkedList<Toppings>();
	}

	public Pizzas(int sizeId) {
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

	public List<Toppings> getToppings() {
		return toppings;
	}

	public void setToppings(List<Toppings> toppings) {
		this.toppings = toppings;
	}

	public void addTopping(Toppings topping) {
		toppings.add(topping);
	}
}
