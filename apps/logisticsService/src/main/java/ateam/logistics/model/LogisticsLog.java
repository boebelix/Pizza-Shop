package ateam.logistics.model;

import ateam.model.entity.User;
import ateam.validator.Validator;

import java.sql.Timestamp;
import java.util.Date;

public class LogisticsLog {
	@Validator.Required
	private int orderId;

	@Validator.Required
	@Validator.Min(1)
	private String firstName;

	@Validator.Required
	@Validator.Min(1)
	private String lastName;

	@Validator.Required
	@Validator.Min(1)
	private String street;

	@Validator.Required
	@Validator.Min(1)
	private String nr;

	@Validator.Required
	@Validator.Min(1)
	private String city;

	@Validator.Required
	@Validator.Min(1)
	private String postalCode;

	@Validator.Required
	@Validator.Min(1)
	private String country;

	@Validator.Required
	@Validator.Min(1)
	private String time;

	public LogisticsLog() {
	}

	public LogisticsLog(User user, int orderId) {
		this.orderId=orderId;
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.street = user.getStreet();
		this.nr = user.getNumber();
		this.city = user.getCity();
		this.postalCode =user.getPostalCode();
		this.country = user.getCountry();
		time =  new Timestamp(new Date().getTime()).toString();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "LogisticsLog{" +
			"orderId=" + orderId +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", street='" + street + '\'' +
			", nr=" + nr +
			", city='" + city + '\'' +
			", postalCode=" + postalCode +
			", country='" + country + '\'' +
			'}';
	}
}
