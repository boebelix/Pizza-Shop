package Model;
import javax.persistence.*;

@Entity
@Table(name ="pizza_topping")
public class PizzaToppingDTO {

	@Id
	@Column(name = "pizza_id")
	private int PizzaId;

	@Id
	@Column(name = "topping_id")
	private int ToppingId;

	@Column(name = "amount")
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
