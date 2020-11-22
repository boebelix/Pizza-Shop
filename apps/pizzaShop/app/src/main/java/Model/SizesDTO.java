package Model;

import javax.persistence.*;

@Entity
@Table(name ="sizes")
public class SizesDTO {

	@Id
	@Column(name = "id")
	private int Id;

	@Column(name = "radius")
	private int Radius;

	@Column(name = "base_price")
	private float BasePrice;

	@Column(name = "topping_price")
	private float topping_price;

	public SizesDTO() {
	}

	public SizesDTO(int id, int radius, float basePrice, float topping_price) {
		Id = id;
		Radius = radius;
		BasePrice = basePrice;
		this.topping_price = topping_price;
	}

	public SizesDTO(int radius, float basePrice, float topping_price) {
		Radius = radius;
		BasePrice = basePrice;
		this.topping_price = topping_price;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getRadius() {
		return Radius;
	}

	public void setRadius(int radius) {
		Radius = radius;
	}

	public float getBasePrice() {
		return BasePrice;
	}

	public void setBasePrice(float basePrice) {
		BasePrice = basePrice;
	}

	public float getTopping_price() {
		return topping_price;
	}

	public void setTopping_price(float topping_price) {
		this.topping_price = topping_price;
	}
}
