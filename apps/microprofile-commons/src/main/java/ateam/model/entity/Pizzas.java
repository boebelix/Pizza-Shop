package ateam.model.entity;

import ateam.validator.Validator;

import java.util.LinkedList;
import java.util.List;

public class Pizzas {

	private int ID;

	@Validator.Required
	private int sizeId;

	@Validator.Required
	List<Toppings> toppings;

	public Pizzas() {
		toppings=new LinkedList<Toppings>();
	}

	public Pizzas(int ID, int sizeId) {
		this.ID = ID;
		this.sizeId = sizeId;
		toppings=new LinkedList<Toppings>();
	}

	public Pizzas(int sizeId) {
		this.sizeId = sizeId;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public int getSizeId() {
		return sizeId;
	}

	public List<Toppings> getToppings() {
		return toppings;
	}

	public void setToppings(List<Toppings> toppings) {
		this.toppings = toppings;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public void addTopping(Toppings topping)
	{
		toppings.add(topping);
	}
}
