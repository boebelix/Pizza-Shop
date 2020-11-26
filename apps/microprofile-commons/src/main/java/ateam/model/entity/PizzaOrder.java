package ateam.model.entity;
import ateam.validator.Validator;

//@Entity
//@Table(name ="orders")
public class PizzaOrder {

	//@Id
	//@Column(name = "order_id")
	@Validator.Required
	private int OrderId;

	//@Id
	//@Column(name = "pizza_id")
	@Validator.Required
	private int PizzaID;

	public PizzaOrder() {
	}

	public PizzaOrder(int orderId, int pizzaID) {
		OrderId = orderId;
		PizzaID = pizzaID;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public int getPizzaID() {
		return PizzaID;
	}

	public void setPizzaID(int pizzaID) {
		PizzaID = pizzaID;
	}

	public static class PizzaTopping {

		@Validator.Required
		private int PizzaId;

		@Validator.Required
		private int ToppingId;

		@Validator.Required
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
}
