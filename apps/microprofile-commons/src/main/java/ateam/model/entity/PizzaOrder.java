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


}
