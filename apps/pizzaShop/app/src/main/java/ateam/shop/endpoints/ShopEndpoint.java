package ateam.shop.endpoints;

import ateam.client.user.UserClient;
import ateam.exceptionmapper.UnauthorizedExceptionMapper;
import ateam.exceptionmapper.ValidationExceptionMapper;
import ateam.model.entity.Order;
import ateam.model.entity.User;
import ateam.model.exception.UnauthorizedException;
import ateam.shop.service.ShopService;
import ateam.validator.ValidationException;
import ateam.validator.Validator;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.UUID;

@RegisterProvider(ValidationExceptionMapper.class)
@RegisterProvider(UnauthorizedExceptionMapper.class)
@Path("/shop")
@Singleton
public class ShopEndpoint {

	@Inject
	private ShopService shopService;

	private final UserClient userClient = RestClientBuilder
		.newBuilder().baseUri(InitialContext.doLookup("UserServiceInternURI")).build(UserClient.class);

	public ShopEndpoint() throws NamingException {}

	@POST
	public Response placeOrder(@HeaderParam("Authorization") UUID loginId, @RequestBody(required = true) Order order) throws UnauthorizedException, ValidationException {
		User user = userClient.getCurrentUser(loginId);
		Validator.validate(order);
		order = shopService.placeOrder(order);
		return Response.status(Response.Status.CREATED).entity(order).build();
	}
}
