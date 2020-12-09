package ateam.test.userService.model;

import ateam.model.entity.User;

public class UserRequest extends User {

	// This shadows the User's original getPassword with a @JsonbTransient annotation, so that
	// JsonB sends the password in the request
	@Override
	public String getPassword() {
		return super.getPassword();
	}
}
