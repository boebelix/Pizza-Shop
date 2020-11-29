package ateam.model.entity;


import ateam.validator.Validator;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Orders {


	@Validator.Required
	List<Pizzas> pizzas;

	private int id;
	@Validator.Required
	private Date orderDate;

	@Validator.Required
	private Date orderArrived;

	@Validator.Required
	@Validator.Min(5)
	@Validator.Max(5)
	@Validator.Regex(regex = "^[0-9]{5}$", errorMessage = "Die PLZ besteht aus 5 zahlen")
	private String postCode;

	@Validator.Required
	@Validator.Regex(regex = "^[A-Z]([^0-9\\§\\%\\&\\!\\?])+?[a-z]$", errorMessage = "Straße muss mit einem Großbuchstaben anfangen und mit einem Kleinbuchstaben enden! Zahlen, so wie die Zeichen '§', '%', '&', '!' und '?' sind nicht erlaubt!")
	private String street;

	@Validator.Required
	private String houseNumber;

	@Validator.Required
	@Validator.Regex(regex = "^[A-Z]([^\\0-9\\§\\%\\&\\!\\?])+?[a-z]$", errorMessage = "Stadt muss mit einem Großbuchstaben anfangen und mit einem Kleinbuchstaben enden! Zahlen, so wie die Zeichen '§', '%', '&', '!' und '?' sind nicht erlaubt!")
	private String city;

	public Orders() {
		pizzas = new LinkedList<Pizzas>();
	}

	public Orders(int id, Date orderDate, Date orderArrived, String PLZ, String street, String houseNumber, String city) {
		this.id = id;
		this.orderDate = orderDate;
		this.orderArrived = orderArrived;
		this.postCode = PLZ;
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
	}

	public Orders(Date orderDate, Date orderArrived, String PLZ, String street, String houseNumber, String city) {
		this.orderDate = orderDate;
		this.orderArrived = orderArrived;
		this.postCode = PLZ;
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
	}

	public List<Pizzas> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizzas> pizzas) {
		this.pizzas = pizzas;
	}

	public String getPLZ() {
		return postCode;
	}

	public void setPLZ(String postCode) {
		this.postCode = postCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCity() {
		return city;
	}

	//getter

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	//setter
	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderArrived() {
		return orderArrived;
	}

	public void setOrderArrived(Date orderArrived) {
		this.orderArrived = orderArrived;
	}

	//additional

	public void addPizza(Pizzas pizza) {
		pizzas.add(pizza);
	}

}

