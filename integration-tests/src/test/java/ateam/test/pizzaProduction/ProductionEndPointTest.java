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
		System.out.println("setUP");
		productionEndpoint = TestUtils.setupClient(TestConstants.PRODUCTION_URI, ProductionEndpoint.class);
	}

	@Test
	void sendOrder() {
		System.out.println("sendOrder");
		ShopProductionItem toCreate = ProductionTestUtils.createDefaultOrder();

		System.out.println("sending"+toCreate.toString());

		Response response = productionEndpoint.produceOrder(toCreate);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void SendEmptyOrderShouldReturnError() {
		System.out.println("sendInvalidPackage");
		ShopProductionItem testItem= ProductionTestUtils.createEmptyOrder();
		System.out.println("sending"+testItem.toString());
		Response conflictResponse = productionEndpoint.produceOrder(testItem);
		System.out.println("sent");
		ServiceResponse<ShopProductionItem> serviceResponse = ServiceResponse.parse(conflictResponse, ShopProductionItem.class);
		System.out.println("Response:"+serviceResponse.hasError());
		assertTrue(serviceResponse.hasError());
		ExceptionResponse exceptionResponse = serviceResponse.getErrorEntity().get();
		System.out.println("Response Error:"+exceptionResponse.getStatus());
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), exceptionResponse.getStatus());
	}
}
