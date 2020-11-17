package Model;

import javax.persistence.*;

@Entity
@Table(name ="pizzas")
public class PizzasDTO {

	@Id
	@Column(name = "id")
	private int ID;

	@Column(name = "size_id")
	private int sizeId;

	public PizzasDTO() {
	}

	public PizzasDTO(int ID, int sizeId) {
		this.ID = ID;
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
