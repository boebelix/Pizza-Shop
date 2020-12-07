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

	@Validator.Required
	private int doughAmount;

	@Validator.Required
	private float topping_factor;

	public Size(int id, int radius, float basePrice, float topping_price, int doughAmount, float topping_factor) {
		this.id = id;
		this.radius = radius;
		this.basePrice = basePrice;
		this.toppingPrice = topping_price;
		this.doughAmount = doughAmount;
		this.topping_factor = topping_factor;
	}

	public Size(int radius, float basePrice, float topping_price, int doughAmount, float topping_factor) {
		this.radius = radius;
		this.basePrice = basePrice;
		this.toppingPrice = topping_price;
		this.doughAmount = doughAmount;
		this.topping_factor = topping_factor;
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

	public int getDoughAmount() {
		return doughAmount;
	}

	public void setDoughAmount(int doughAmount) {
		this.doughAmount = doughAmount;
	}

	public float getTopping_factor() {
		return topping_factor;
	}

	public void setTopping_factor(float topping_factor) {
		this.topping_factor = topping_factor;
	}
}
