package ateam.test.userService;

import ateam.model.entity.User;
import ateam.test.userService.model.UserRequest;

public class UserServiceTestUtils {

	public static User createDefaultUser(final String username) {
		return UserRequest.builder()
			.username(username)
			.email(username + "@stud.hs-kl.de")
			.password("123456789#!TesT")
			.firstName("Max")
			.lastName("Mustermann")
			.street("Amerikastraße")
			.number("42")
			.city("Zweibrücken")
			.postalCode("66482")
			.country("Deutschland")
			.build();
	}

}
