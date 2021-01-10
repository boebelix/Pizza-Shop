package ateam.model.entity;

import ateam.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class Pizza {

	@Validator.Required
	@Validator.Min(1)
	@Validator.Valid
	private List<PizzaTopping> toppings;

	private int id;

	@Validator.Required
	private int sizeId;

	private int orderId;

	public Pizza() {
		this.toppings = new ArrayList<>();
	}

	public Pizza(int id, int sizeId, int orderId, List<PizzaTopping> toppings) {
		this.id = id;
		this.sizeId = sizeId;
		this.orderId = orderId;
		this.toppings = toppings;
	}

	public Pizza(int sizeId) {
		this.sizeId = sizeId;
		this.toppings = new ArrayList<>();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Pizza{" +
			"toppings=" + toppings +
			", id=" + id +
			", sizeId=" + sizeId +
			", orderId=" + orderId +
			'}';
	}
}
