package ateam.model.entity;

import ateam.validator.Validator.Required;

public class PizzaOrderTopping {

	@Required
	private int ToppingId;

	@Required
	private int Amount;

	@Required
	private int PizzaOrderId;

	public PizzaOrderTopping() {
	}

	public PizzaOrderTopping(int PizzaOrderId, int toppingId, int amount) {
		this.PizzaOrderId = PizzaOrderId;
		ToppingId = toppingId;
		Amount = amount;
	}

	public int getPizzaOrderId() {
		return PizzaOrderId;
	}

	public void setPizzaId(int pizzaOrderId) {
		PizzaOrderId = pizzaOrderId;
	}

	public int getToppingId() {
		return ToppingId;
	}

	public void setToppingId(int toppingId) {
		ToppingId = toppingId;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}
}
