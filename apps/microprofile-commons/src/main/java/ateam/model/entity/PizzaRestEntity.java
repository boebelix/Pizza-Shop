package ateam.model.entity;

import ateam.validator.Validator;

import java.util.List;

public class PizzaRestEntity {
	@Validator.Required
	int ID;

	@Validator.Required
	@Validator.Min(1)
	int doughAmount;

	@Validator.Required
	double toppingFactor;

	@Validator.Required
	List<Topping> toppings;

	public PizzaRestEntity(int ID, int doughAmount, double toppingFactor, List<Topping> toppings) {
		this.ID = ID;
		this.doughAmount = doughAmount;
		this.toppingFactor = toppingFactor;
		this.toppings = toppings;
	}
	public PizzaRestEntity() {
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public int getDoughAmount() {
		return doughAmount;
	}

	public void setDoughAmount(int doughAmount) {
		this.doughAmount = doughAmount;
	}

	public double getToppingFactor() {
		return toppingFactor;
	}

	public void setToppingFactor(double toppingFactor) {
		this.toppingFactor = toppingFactor;
	}

	public List<Topping> getToppings() {
		return toppings;
	}

	public void setToppings(List<Topping> toppings) {
		this.toppings = toppings;
	}

	public void addTopping(Topping topping)
	{
		this.toppings.add(topping);
	}

	public Topping getTopping(int index)
	{
		return this.toppings.get(index);
	}

	@Override
	public String toString() {
		String data= "PizzaRestEntity{" +
			"ID=" + ID +
			", doughAmount=" + doughAmount +
			", toppingFactor=" + toppingFactor +
			", toppings=";
		for(Topping t : toppings)
			data += t.toString();

		return data + '}';
	}
}
