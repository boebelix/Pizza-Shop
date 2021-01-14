package ateam.test.pizzaProduction;

import ateam.client.production.ProductionClient;
import ateam.model.entity.ShopProductionItem;
import ateam.model.entity.User;
import ateam.model.exception.ExceptionResponse;
import ateam.test.userService.UserServiceTestUtils;
import ateam.test.util.ServiceResponse;
import ateam.test.util.TestConstants;
import ateam.test.util.TestUtils;
import ateam.production.endpoint.ProductionEndpoint;
import ateam.user.endpoints.UserEndpoint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductionEndPointTest {

	private static ProductionClient productionClient;
	private static UserEndpoint userEndpoint;
	private static int userId;

	@BeforeAll
	public static void setupClass() {
		productionClient = TestUtils.setupClient(TestConstants.PRODUCTION_URI, ProductionClient.class);
		userEndpoint = TestUtils.setupClient(TestConstants.USER_SERVICE_URI, UserEndpoint.class);
		User toCreate = UserServiceTestUtils.createDefaultUser("productionControllerTest");
		Response response = userEndpoint.createUser(toCreate);
		ServiceResponse<User> serviceResponse = ServiceResponse.parse(response, User.class);
		userId = serviceResponse.getResponseEntity().get().getUserId();
	}

	@Test
	void sendOrder() throws IOException {

		ShopProductionItem toCreate = ProductionTestUtils.createDefaultOrder(userId);

		Response response = productionClient.produceOrder(toCreate);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void SendEmptyOrderShouldReturnError() throws IOException {
		ShopProductionItem testItem = ProductionTestUtils.createEmptyOrder();


		assertThrows(BadRequestException.class, () -> {
			Response conflictResponse = productionClient.produceOrder(testItem);
			ServiceResponse<ShopProductionItem> serviceResponse = ServiceResponse.parse(conflictResponse, ShopProductionItem.class);

			assertTrue(serviceResponse.hasError());

			ExceptionResponse exceptionResponse = serviceResponse.getErrorEntity().get();
			assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), exceptionResponse.getStatus());
		}
		);
	}
}
