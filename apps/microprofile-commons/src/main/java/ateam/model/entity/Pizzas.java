package ateam.model.entity;
import ateam.validator.Validator;

public class Pizzas {

	private int ID;

	@Validator.Required
	private int sizeId;

	public Pizzas() {
	}

	public Pizzas(int ID, int sizeId) {
		this.ID = ID;
		this.sizeId = sizeId;
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

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
}
