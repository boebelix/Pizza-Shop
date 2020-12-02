package ateam.model.entity;

import ateam.validator.Validator;

public class Size {

	private int id;

	@Validator.Required
	private int radius;

	@Validator.Required
	private float basePrice;

	@Validator.Required
	private float toppingPrice;

	public Size(int id, int radius, float basePrice, float topping_price) {
		this.id = id;
		this.radius = radius;
		this.basePrice = basePrice;
		this.toppingPrice = topping_price;
	}

	public Size(int radius, float basePrice, float topping_price) {
		this.radius = radius;
		this.basePrice = basePrice;
		this.toppingPrice = topping_price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public float getTopping_price() {
		return toppingPrice;
	}

	public void setTopping_price(float toppingPrice) {
		this.toppingPrice = toppingPrice;
	}
}
