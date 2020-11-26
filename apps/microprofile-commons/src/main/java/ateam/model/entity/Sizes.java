package ateam.model.entity;

import ateam.validator.Validator;

public class Sizes {

	private int Id;

	@Validator.Required
	private int Radius;

	@Validator.Required
	private float BasePrice;

	@Validator.Required
	private float topping_price;

	public Sizes() {
	}

	public Sizes(int id, int radius, float basePrice, float topping_price) {
		Id = id;
		Radius = radius;
		BasePrice = basePrice;
		this.topping_price = topping_price;
	}

	public Sizes(int radius, float basePrice, float topping_price) {
		Radius = radius;
		BasePrice = basePrice;
		this.topping_price = topping_price;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getRadius() {
		return Radius;
	}

	public void setRadius(int radius) {
		Radius = radius;
	}

	public float getBasePrice() {
		return BasePrice;
	}

	public void setBasePrice(float basePrice) {
		BasePrice = basePrice;
	}

	public float getTopping_price() {
		return topping_price;
	}

	public void setTopping_price(float topping_price) {
		this.topping_price = topping_price;
	}
}
