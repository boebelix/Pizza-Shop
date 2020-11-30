package ateam.model.entity;

import ateam.validator.Validator;

import javax.json.bind.annotation.JsonbTransient;
import java.util.Date;

public class User {

	private int userId;

	@Validator.Required(errorMessage = "Nutzername benötigt!")
	@Validator.Min(value = 1, errorMessage = "Das Feld \"Nutzername\" darf nicht leer sein!")
	private String username;

	@Validator.Required(errorMessage = "Vorname benötigt!")
	@Validator.Min(value = 1, errorMessage = "Das Feld \"Vorname\" darf nicht leer sein!")
	private String firstName;

	@Validator.Required(errorMessage = "Nachname benötigt!")
	@Validator.Min(value = 1, errorMessage = "Das Feld \"Nachname\" darf nicht leer sein!")
	private String lastName;

	@Validator.Required(errorMessage = "Email benötigt!")
	@Validator.Regex(regex = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.," +
		";:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])" +
		"|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", errorMessage = "Gültige Email benötigt!")
	private String email;

	@Validator.Regex(regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])" +
		"(?=.*[*.!@$%^&(){}\\[\\]:;<>,.?\\/~_+\\-=|\\\\]).{5,}$",
		errorMessage = "Das Passwort muss aus mindestens einen Kleinbuchstaben, " +
			"einen Großbuchstaben, einer Zahl und einem Sonderzeichen bestehen, " +
			"sowie mindestens 5 Zeichen lang sein!")
	private String password;

	@Validator.Required(errorMessage = "Postleitzahl benötigt!")
	@Validator.Regex(regex = "^[0-9]{5}$", errorMessage = "Postleitzahl muss aus 5 Zahlen bestehen!")
	private String postalCode;

	@Validator.Required(errorMessage = "Straße benötigt!")
	@Validator.Min(value = 1, errorMessage = "Das Feld \"Straße\" darf nicht leer sein!")
	private String street;

	@Validator.Required(errorMessage = "Hausnummer benötigt!")
	@Validator.Regex(regex = "^[0-9]+(( )?[A-Za-z])?$", errorMessage = "Hausnummer ungültig!")
	private String number;

	@Validator.Required(errorMessage = "Stadt benötigt!")
	@Validator.Min(value = 1, errorMessage = "Das Feld \"Stadt\" darf nicht leer sein!")
	private String city;

	@Validator.Required(errorMessage = "Land benötigt!")
	@Validator.Min(value = 1, errorMessage = "Das Feld \"Land\" darf nicht leer sein!")
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
