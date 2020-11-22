package Model;


public class PizzaToppingDTO {

	@Validator.Required
	private int PizzaId;

	@Validator.Required
	private int ToppingId;

	@Validator.Required
	private int Amount;

	public PizzaToppingDTO() {
	}

	public PizzaToppingDTO(int pizzaId, int toppingId, int amount) {
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
