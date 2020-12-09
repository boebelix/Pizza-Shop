package ateam.test.userService;

import ateam.model.entity.User;
import ateam.model.exception.ExceptionResponse;
import ateam.test.util.ServiceResponse;
import ateam.test.util.TestConstants;
import ateam.test.util.TestUtils;
import ateam.user.endpoints.UserEndpoint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UserEndpointTest {

	private static UserEndpoint userEndpoint;

	@BeforeAll
	public static void setupClass() {
		userEndpoint = TestUtils.setupClient(TestConstants.USER_SERVICE_URI, UserEndpoint.class);
	}

	@Test
	void createUser() {
		User toCreate = UserServiceTestUtils.createDefaultUser("createUser");
		Response response = userEndpoint.createUser(toCreate);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void createUserTwice_shouldConflict() {
		User toCreate = UserServiceTestUtils.createDefaultUser("createUser_shouldConflict");
		userEndpoint.createUser(toCreate);
		Response conflictResponse = userEndpoint.createUser(toCreate);

		ServiceResponse<User> serviceResponse = ServiceResponse.parse(conflictResponse, User.class);
		assertTrue(serviceResponse.hasError());
		ExceptionResponse exceptionResponse = serviceResponse.getErrorEntity().get();
		assertEquals(Response.Status.CONFLICT.getStatusCode(), exceptionResponse.getStatus());
		assertTrue(exceptionResponse.getMessage().startsWith("Es existiert bereits ein Nutzer"));
	}

}
