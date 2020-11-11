package Model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name ="orders")
public class PizzaOrderDTO {

	@Id
	@Column(name = "order_id")
	private int OrderId;

	@Id
	@Column(name = "pizza_id")
	private int PizzaID;

	public PizzaOrderDTO() {
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
