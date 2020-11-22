package Model;

import javax.persistence.*;

@Entity
@Table(name ="pizza_topping")

public class ToppingsDTO {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String Name;

	public ToppingsDTO() {
	}

	public ToppingsDTO(int id, String name) {
		this.id = id;
		Name = name;
	}
	public ToppingsDTO( String name) {
		this.id = id;
		Name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
