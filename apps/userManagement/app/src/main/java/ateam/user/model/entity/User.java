package ateam.user.model.entity;

import ateam.validator.Validator;

import javax.json.bind.annotation.JsonbTransient;
import java.util.Date;

public class User {

	private int userId;

	@Validator.Required()
	@Validator.Regex(regex = "^.+$", errorMessage = "Nutzername benötigt!")
	private String username;

	@Validator.Required
	@Validator.Regex(regex = "^[A-Z]([^0-9\\§\\%\\&\\!\\?])+?[a-z]$", errorMessage = "Vorname muss mit einem Großbuchstaben anfangen und mit einem Kleinbuchstaben enden! Die Zeichen '§', '%', '&', '!' und '?' sind nicht erlaubt!")
	private String firstName;

	@Validator.Required
	@Validator.Regex(regex = "^[A-Z]([^0-9\\§\\%\\&\\!\\?])+?[a-z]$", errorMessage = "Vorname muss mit einem Großbuchstaben anfangen und mit einem Kleinbuchstaben enden! Die Zeichen '§', '%', '&', '!' und '?' sind nicht erlaubt!")
	private String lastName;

	@Validator.Required()
	@Validator.Regex(regex = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$", errorMessage = "Email benötigt!")
	private String email;

	@Validator.Required
	@Validator.Regex(regex = "^.+$", errorMessage = "Passwort benötigt!")
	private String password;

	@Validator.Required
	@Validator.Regex(regex = "^[0-9]{5,5}$", errorMessage = "Postleitzahl muss aus 5 Zahlen bestehen!")
	private String postalCode;

	@Validator.Required
	@Validator.Regex(regex = "^.+$", errorMessage = "Straße benötigt!")
	private String street;

	@Validator.Required(errorMessage = "Hausnummer benötigt!")
	@Validator.Regex(regex = "^[0-9]+$", errorMessage = "HausNUMMER benötigt!")
	private String number;

	@Validator.Required
	@Validator.Regex(regex = "^.+$", errorMessage = "Stadt benötigt!")
	private String city;

	@Validator.Required
	@Validator.Regex(regex = "^.+$", errorMessage = "Land benötigt!")
	private String country;

	private Date createAt;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonbTransient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createDt) {
		this.createAt = createDt;
	}
}
