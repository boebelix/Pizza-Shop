package ateam.model.entity;

import ateam.validator.Validator;

import java.util.LinkedList;
import java.util.List;

public class Pizzas {

	private int iD;

	@Validator.Required
	private int sizeId;

	@Validator.Required
	List<Toppings> toppings;

	@Validator.Required
	private int orderId;

	public Pizzas() {
		toppings=new LinkedList<Toppings>();
	}

	public Pizzas(int iD, int sizeId,int orderId) {
		this.iD = iD;
		this.sizeId = sizeId;
		this.orderId=orderId;
		toppings=new LinkedList<Toppings>();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Pizzas(int sizeId) {
		this.sizeId = sizeId;
	}

	public int getID() {
		return iD;
	}

	public void setID(int iD) {
		this.iD = iD;
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
