package ateam.model.entity;


import ateam.validator.Validator;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Order {

	@Validator.Required
	@Validator.Valid
	List<Pizza> pizzas;

	private int id;

	private Date orderDate;

	private Date orderArrived;

	@Validator.Required
	@Validator.Regex(regex = "^[0-9]{5}$", errorMessage = "Die PLZ besteht aus 5 zahlen")
	private String postCode;

	@Validator.Required
	@Validator.Min(value = 1, errorMessage = "Das Feld \"Straße\" darf nicht leer sein!")
	private String street;

	@Validator.Required
	@Validator.Min(value = 1, errorMessage = "Das Feld \"Hausnummer\" darf nicht leer sein!")
	private String houseNumber;

	@Validator.Required
	@Validator.Min(value = 1, errorMessage = "Das Feld \"Stadt\" darf nicht leer sein!")
	private String city;

	public Order() {
		pizzas = new LinkedList<Pizza>();
	}

	public Order(int id, Date orderDate, Date orderArrived, String PLZ, String street, String houseNumber, String city) {
		this.id = id;
		this.orderDate = orderDate;
		this.orderArrived = orderArrived;
		this.postCode = PLZ;
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
	}

	public Order(Date orderDate, Date orderArrived, String PLZ, String street, String houseNumber, String city) {
		this.orderDate = orderDate;
		this.orderArrived = orderArrived;
		this.postCode = PLZ;
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
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

	public void addPizza(Pizza pizza) {
		pizzas.add(pizza);
	}

}

