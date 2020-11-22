package Model;

//@Entity
//@Table(name ="orders")
public class PizzaOrderDTO {

	//@Id
	//@Column(name = "order_id")
	@Validator.Required
	private int OrderId;

	//@Id
	//@Column(name = "pizza_id")
	@Validator.Required
	private int PizzaID;

	public PizzaOrderDTO() {
	}

	public PizzaOrderDTO(int orderId, int pizzaID) {
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
