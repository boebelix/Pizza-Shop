package ateam.model.entity;


import ateam.validator.Validator;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Orders {


	@Validator.Required
	List<Pizzas> pizzas;
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

	public Orders() {
		pizzas = new LinkedList<Pizzas>();
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

	public List<Pizzas> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizzas> pizzas) {
		this.pizzas = pizzas;
	}

	public String getPLZ() {
		return PLZ;
	}

	public void setPLZ(String PLZ) {
		this.PLZ = PLZ;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getHouseNumber() {
		return HouseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		HouseNumber = houseNumber;
	}

	public String getCity() {
		return City;
	}

	//getter

	public void setCity(String city) {
		City = city;
	}

	public int getId() {
		return Id;
	}

	//setter
	public void setId(int id) {
		Id = id;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public Date getOrderArrived() {
		return OrderArrived;
	}

	public void setOrderArrived(Date orderArrived) {
		OrderArrived = orderArrived;
	}

	//additional

	public void addPizza(Pizzas pizza) {
		pizzas.add(pizza);
	}

}

