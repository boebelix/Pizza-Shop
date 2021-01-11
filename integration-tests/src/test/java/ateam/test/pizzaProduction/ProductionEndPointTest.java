package ateam.test.pizzaProduction;

import ateam.client.production.ProductionClient;
import ateam.model.entity.ShopProductionItem;
import ateam.model.exception.ExceptionResponse;
import ateam.test.util.ServiceResponse;
import ateam.test.util.TestConstants;
import ateam.test.util.TestUtils;
import ateam.production.endpoint.ProductionEndpoint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductionEndPointTest {

	private static ProductionClient productionClient;

	@BeforeAll
	public static void setupClass() {
		System.out.println("setUP");
		productionClient = TestUtils.setupClient(TestConstants.PRODUCTION_URI, ProductionClient.class);
	}

	@Test
	void sendOrder() throws IOException {
		System.out.println("sendOrder");
		ShopProductionItem toCreate = ProductionTestUtils.createDefaultOrder();

		System.out.println("sending"+toCreate.toString());

		Response response = productionClient.produceOrder(toCreate);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Test
	void SendEmptyOrderShouldReturnError() throws IOException {
		System.out.println("sendInvalidPackage");
		ShopProductionItem testItem= ProductionTestUtils.createEmptyOrder();
		System.out.println("sending"+testItem.toString());
		Response conflictResponse = productionClient.produceOrder(testItem);

		System.out.println("sent");
		ServiceResponse<ShopProductionItem> serviceResponse = ServiceResponse.parse(conflictResponse, ShopProductionItem.class);
		System.out.println("Response:"+serviceResponse.hasError());
		assertTrue(serviceResponse.hasError());
		ExceptionResponse exceptionResponse = serviceResponse.getErrorEntity().get();
		System.out.println("Response Error:"+exceptionResponse.getStatus());
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), exceptionResponse.getStatus());
	}
}
