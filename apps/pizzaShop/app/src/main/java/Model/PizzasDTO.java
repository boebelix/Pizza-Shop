package Model;


public class PizzasDTO {

	private int ID;

	@Validator.Required
	private int sizeId;

	public PizzasDTO() {
	}

	public PizzasDTO(int ID, int sizeId) {
		this.ID = ID;
		this.sizeId = sizeId;
	}

	public PizzasDTO( int sizeId) {
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
