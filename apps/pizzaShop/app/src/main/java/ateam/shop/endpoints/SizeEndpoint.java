package ateam.shop.endpoints;

import ateam.exceptionmapper.ShopExceptionMapper;
import ateam.shop.service.PizzaService;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/size")
@RegisterProvider(ShopExceptionMapper.class)
@Singleton
public class SizeEndpoint {

	@Inject
	private PizzaService pizzaService;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSizes() {
		return Response.ok(pizzaService.getSizes()).build();
	}
}
