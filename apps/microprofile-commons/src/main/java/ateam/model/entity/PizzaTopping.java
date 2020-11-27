package ateam.model.entity;

import ateam.validator.Validator.Required;

public class PizzaTopping {

	@Required
	private int toppingId;

	@Required
	private int amount;

	@Required
	private int pizzaId;

	public PizzaTopping() {
	}

	public PizzaTopping(int pizzaId, int toppingId, int amount) {
		this.pizzaId = pizzaId;
		this.toppingId = toppingId;
		this.amount = amount;
	}

	public int getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(int pizzaOrderId) {
		pizzaId = pizzaOrderId;
	}

	public int getToppingId() {
		return toppingId;
	}

	public void setToppingId(int toppingId) {
		this.toppingId = toppingId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
