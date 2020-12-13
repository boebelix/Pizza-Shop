package ateam.test.pizzaProduction;

import ateam.model.entity.ShopProductionItem;
import ateam.model.exception.ExceptionResponse;
import ateam.test.util.ServiceResponse;
import ateam.test.util.TestConstants;
import ateam.test.util.TestUtils;
import ateam.endpoint.ProductionEndpoint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductionEndPointTest {

	private static ProductionEndpoint productionEndpoint;

	@BeforeAll
	public static void setupClass() {
		productionEndpoint = TestUtils.setupClient(TestConstants.PRODUCTION_URI, ProductionEndpoint.class);
	}

	@Test
	void sendOrder() {
		ShopProductionItem toCreate = ProductionTestUtils.createDefaultOrder();
		Response response = productionEndpoint.produceOrder(toCreate);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void SendEmptyOrderShouldReturnError() {
		ShopProductionItem testItem= ProductionTestUtils.createEmptyOrder();
		Response conflictResponse = productionEndpoint.produceOrder(testItem);

		ServiceResponse<ShopProductionItem> serviceResponse = ServiceResponse.parse(conflictResponse, ShopProductionItem.class);
		assertTrue(serviceResponse.hasError());
		ExceptionResponse exceptionResponse = serviceResponse.getErrorEntity().get();
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), exceptionResponse.getStatus());
	}
}
