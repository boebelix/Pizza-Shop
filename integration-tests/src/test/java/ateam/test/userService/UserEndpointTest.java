package ateam.test.userService;

import ateam.test.userService.model.UserRequest;
import ateam.test.util.TestConstants;
import ateam.test.util.TestUtils;
import ateam.user.endpoints.UserEndpoint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UserEndpointTest {

	private static UserEndpoint userEndpoint;

	@BeforeAll
	public static void setupClass() {
		userEndpoint = TestUtils.setupClient(TestConstants.USER_SERVICE_URI, UserEndpoint.class);
	}

	@Test
	void createUser() {
		UserRequest toCreate = new UserRequest();
		toCreate.setCity("Zweibrücken");
		toCreate.setCountry("Deutschland");
		toCreate.setEmail("test@stud.hs-kl.de");
		toCreate.setNumber("42");
		toCreate.setStreet("Amerikastraße");
		toCreate.setPostalCode("66482");
		toCreate.setFirstName("Max");
		toCreate.setLastName("Mustermann");
		toCreate.setPassword("123456789#!TesT");
		toCreate.setUsername("studi");

		Response response = userEndpoint.createUser(toCreate);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

}
