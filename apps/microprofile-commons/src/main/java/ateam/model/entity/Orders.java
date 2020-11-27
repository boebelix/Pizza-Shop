
package ateam.model.entity;


import ateam.validator.Validator;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Orders {


	private int Id;

	@Validator.Required
	private Date OrderDate;

	@Validator.Required
	private Date OrderArrived;

	@Validator.Required
	@Validator.Min(5)
	@Validator.Max(5)
	@Validator.Regex(regex = "^[0-9]{5}$", errorMessage = "Die PLZ besteht aus 5 zahlen")
	private String PLZ;

	@Validator.Required
	@Validator.Regex(regex = "^[A-Z]([^0-9\\§\\%\\&\\!\\?])+?[a-z]$", errorMessage = "Straße muss mit einem Großbuchstaben anfangen und mit einem Kleinbuchstaben enden! Zahlen, so wie die Zeichen '§', '%', '&', '!' und '?' sind nicht erlaubt!")
	private String Street;

	@Validator.Required
	private String HouseNumber;

	@Validator.Required
	@Validator.Regex(regex = "^[A-Z]([^\\0-9\\§\\%\\&\\!\\?])+?[a-z]$", errorMessage = "Stadt muss mit einem Großbuchstaben anfangen und mit einem Kleinbuchstaben enden! Zahlen, so wie die Zeichen '§', '%', '&', '!' und '?' sind nicht erlaubt!")
	private String City;

	@Validator.Required
	List<Pizzas> pizzas;

	public List<Pizzas> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizzas> pizzas) {
		this.pizzas = pizzas;
	}

	public Orders()
	{
		pizzas =new LinkedList<Pizzas>();
	}

	public Orders(int id, Date orderDate, Date orderArrived, String PLZ, String street, String houseNumber, String city) {
		Id = id;
		OrderDate = orderDate;
		OrderArrived = orderArrived;
		this.PLZ = PLZ;
		Street = street;
		HouseNumber = houseNumber;
		City = city;
	}

	public Orders(Date orderDate, Date orderArrived, String PLZ, String street, String houseNumber, String city) {
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

	//additional

	public void addPizza(Pizzas pizza)
	{
		pizzas.add(pizza);
	}

}

