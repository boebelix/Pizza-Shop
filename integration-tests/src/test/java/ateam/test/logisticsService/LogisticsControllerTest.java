package ateam.test.logisticsService;

import ateam.client.logistics.LogisticsClient;
import ateam.model.entity.LogisticsPostInput;
import ateam.model.entity.User;
import ateam.model.exception.UnauthorizedException;
import ateam.test.userService.UserServiceTestUtils;
import ateam.test.util.ServiceResponse;
import ateam.test.util.TestConstants;
import ateam.test.util.TestUtils;
import ateam.user.endpoints.UserEndpoint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.naming.NamingException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

public class LogisticsControllerTest {
	private static UserEndpoint userEndpoint;
	private static LogisticsClient logisticsClient;
	private static int userId;

	@BeforeAll
	public static void setupClass() {
		logisticsClient = TestUtils.setupClient(TestConstants.LOGISTICS_SERVICE_URI, LogisticsClient.class);

		userEndpoint = TestUtils.setupClient(TestConstants.USER_SERVICE_URI, UserEndpoint.class);
		User toCreate = UserServiceTestUtils.createDefaultUser("logisticsControllerTest3");
		Response response = userEndpoint.createUser(toCreate);
		ServiceResponse<User> serviceResponse = ServiceResponse.parse(response, User.class);
		userId = serviceResponse.getResponseEntity().get().getUserId();
	}

	@Test
	void createTestLogisticsLog() throws URISyntaxException, NamingException, UnauthorizedException, IOException {
		LogisticsPostInput testInput = new LogisticsPostInput();
		testInput.setOrderId(1234);
		testInput.setUserId(userId);
		Response response = logisticsClient.createLogisticsLog(testInput);

		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
	}
}
