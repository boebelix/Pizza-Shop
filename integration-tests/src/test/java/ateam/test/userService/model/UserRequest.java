package ateam.test.userService.model;

import ateam.model.entity.User;

public class UserRequest extends User {

	// This shadows the User's original getPassword with a @JsonbTransient annotation, so that
	// JsonB sends the password in the request
	@Override
	public String getPassword() {
		return super.getPassword();
	}


	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String username;
		private String firstName;
		private String lastName;
		private String email;
		private String password;
		private String postalCode;
		private String street;
		private String number;
		private String city;
		private String country;

		private Builder() {
		}

		public Builder username(final String username) {
			this.username = username;
			return this;
		}

		public Builder firstName(final String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(final String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder email(final String email) {
			this.email = email;
			return this;
		}

		public Builder password(final String password) {
			this.password = password;
			return this;
		}

		public Builder postalCode(final String postalCode) {
			this.postalCode = postalCode;
			return this;
		}

		public Builder street(final String street) {
			this.street = street;
			return this;
		}

		public Builder number(final String number) {
			this.number = number;
			return this;
		}

		public Builder city(final String city) {
			this.city = city;
			return this;
		}

		public Builder country(final String country) {
			this.country = country;
			return this;
		}

		public UserRequest build() {
			UserRequest userRequest = new UserRequest();
			userRequest.setUsername(username);
			userRequest.setFirstName(firstName);
			userRequest.setLastName(lastName);
			userRequest.setEmail(email);
			userRequest.setPassword(password);
			userRequest.setPostalCode(postalCode);
			userRequest.setStreet(street);
			userRequest.setNumber(number);
			userRequest.setCity(city);
			userRequest.setCountry(country);
			return userRequest;
		}
	}
}
