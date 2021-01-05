package ateam.model.entity;

import ateam.validator.Validator;

//Validations are not necessary, because new sized can't be created by the user. That's why there are no errormessages
public class Size {

	private int id;

	@Validator.Required
	@Validator.Min(1)
	private int radius;

	@Validator.Required
	@Validator.Min(0)
	private float basePrice;

	@Validator.Required
	@Validator.Min(0)
	private float toppingPrice;

	@Validator.Required
	@Validator.Min(1)
	private int doughAmount;

	@Validator.Required
	@Validator.Min(0)
	private float toppingFactor;

	public Size(int id, int radius, float basePrice, float toppingPrice, int doughAmount, float toppingFactor) {
		this.id = id;
		this.radius = radius;
		this.basePrice = basePrice;
		this.toppingPrice = toppingPrice;
		this.doughAmount = doughAmount;
		this.toppingFactor = toppingFactor;
	}

	public Size(int radius, float basePrice, float topping_price, int doughAmount, float toppingFactor) {
		this.radius = radius;
		this.basePrice = basePrice;
		this.toppingPrice = topping_price;
		this.doughAmount = doughAmount;
		this.toppingFactor = toppingFactor;
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

	public float getToppingPrice() {
		return toppingPrice;
	}

	public void setToppingPrice(float toppingPrice) {
		this.toppingPrice = toppingPrice;
	}

	public int getDoughAmount() {
		return doughAmount;
	}

	public void setDoughAmount(int doughAmount) {
		this.doughAmount = doughAmount;
	}

	public float getToppingFactor() {
		return toppingFactor;
	}

	public void setToppingFactor(float toppingFactor) {
		this.toppingFactor = toppingFactor;
	}
}
