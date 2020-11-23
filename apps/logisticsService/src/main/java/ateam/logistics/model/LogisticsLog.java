package ateam.logistics.model;

public class LogisticsLog {
	private int orderId = -1;
	private String firstName;
	private String lastName;
	private String street;
	private int nr = -1;
	private String city;
	private int postalCode = -1;
	private String country;

	public LogisticsLog() {
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

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean checkValid() {
		if (orderId == -1 || firstName == null || lastName == null || street == null || nr == -1 || city == null || postalCode == -1 || country == null) {
			return false;
		} else {
			return true;
		}
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
