package ateam.user.model.entity;

import ateam.validator.Validator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users",
	uniqueConstraints = {
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "username")
	})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int userId;

	@Validator.Required()
	@Validator.Regex(regex = "^.+$", errorMessage = "Nutzername benötigt!")
	@Column(name = "username")
	private String username;

	@Validator.Required
	@Validator.Regex(regex = "^[A-Z]([^0-9\\§\\%\\&\\!\\?])+?[a-z]$", errorMessage = "Vorname muss mit einem Großbuchstaben anfangen und mit einem Kleinbuchstaben enden! Die Zeichen '§', '%', '&', '!' und '?' sind nicht erlaubt!")
	@Column(name = "first_name")
	private String firstName;

	@Validator.Required
	@Validator.Regex(regex = "^[A-Z]([^0-9\\§\\%\\&\\!\\?])+?[a-z]$", errorMessage = "Vorname muss mit einem Großbuchstaben anfangen und mit einem Kleinbuchstaben enden! Die Zeichen '§', '%', '&', '!' und '?' sind nicht erlaubt!")
	@Column(name = "last_name")
	private String lastName;

	@Validator.Required()
	@Validator.Regex(regex = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$", errorMessage = "Email benötigt!")
	@Column(name = "email")
	private String email;

	@Validator.Required
	@Validator.Regex(regex = "^.+$", errorMessage = "Passwort benötigt!")
	@Column(name = "password")
	private String password;

	@Validator.Required
	@Validator.Regex(regex = "^[0-9]{5,5}$", errorMessage = "Postleitzahl muss aus 5 Zahlen bestehen!")
	@Column(name = "postal_code")
	private String postalCode;

	@Validator.Required
	@Validator.Regex(regex = "^.+$", errorMessage = "Straße benötigt!")
	@Column(name = "street")
	private String street;

	@Validator.Required(errorMessage = "Hausnummer benötigt!")
	@Validator.Regex(regex = "^[0-9]+$", errorMessage = "HausNUMMER benötigt!")
	@Column(name = "number")
	private String number;

	@Validator.Required
	@Validator.Regex(regex = "^.+$", errorMessage = "Stadt benötigt!")
	@Column(name = "city")
	private String city;

	@Validator.Required
	@Validator.Regex(regex = "^.+$", errorMessage = "Land benötigt!")
	@Column(name = "country")
	private String country;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "create_dt")
	private Date createDt;

	@PrePersist
	protected void onCreate() {
		createDt = new Date();
	}


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

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
}
