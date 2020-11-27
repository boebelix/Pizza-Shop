package ateam.model.entity;

import ateam.validator.Validator.Required;

public class PizzaTopping {

	@Required
	private int PizzaId;

	@Required
	private int ToppingId;

	@Required
	private int Amount;

	public PizzaTopping() {
	}

	public PizzaTopping(int pizzaId, int toppingId, int amount) {
		PizzaId = pizzaId;
		ToppingId = toppingId;
		Amount = amount;
	}

	public int getPizzaId() {
		return PizzaId;
	}

	public void setPizzaId(int pizzaId) {
		PizzaId = pizzaId;
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
