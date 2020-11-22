
package Model;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name ="orders")
public class OrdersDTO {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int Id;

	@Column(name = "order_date")
	private Date OrderDate;

	@Column(name = "order_arrived")
	private Date OrderArrived;

	@Column(name = "postal_code")
	private String PLZ;

	@Column(name = "street")
	private String Street;

	@Column(name = "houseNumber")
	private String HouseNumber;

	@Column(name = "city")
	private String City;

	public OrdersDTO()
	{

	}

	public OrdersDTO(int id, Date orderDate, Date orderArrived, String PLZ, String street, String houseNumber, String city) {
		Id = id;
		OrderDate = orderDate;
		OrderArrived = orderArrived;
		this.PLZ = PLZ;
		Street = street;
		HouseNumber = houseNumber;
		City = city;
	}

	public OrdersDTO(Date orderDate, Date orderArrived, String PLZ, String street, String houseNumber, String city) {
		OrderDate = orderDate;
		OrderArrived = orderArrived;
		this.PLZ = PLZ;
		Street = street;
		HouseNumber = houseNumber;
		City = city;
	}

	//setter
	public void setId(int id) {
		Id = id;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public void setOrderArrived(Date orderArrived) {
		OrderArrived = orderArrived;
	}

	public void setPLZ(String PLZ) {
		this.PLZ = PLZ;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public void setCity(String city) {
		City = city;
	}

	public void setHouseNumber(String houseNumber) {
		HouseNumber = houseNumber;
	}

	//getter

	public String getPLZ() {
		return PLZ;
	}

	public String getStreet() {
		return Street;
	}

	public String getHouseNumber() {
		return HouseNumber;
	}

	public String getCity() {
		return City;
	}

	public int getId() {
		return Id;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public Date getOrderArrived() {
		return OrderArrived;
	}

}

