package ateam.user.endpoints;

import ateam.exceptionmapper.ConflictExceptionMapper;
import ateam.exceptionmapper.UnauthorizedExceptionMapper;
import ateam.exceptionmapper.ValidatorExceptionMapper;
import ateam.model.entity.User;
import ateam.model.exception.UnauthorizedException;
import ateam.user.service.AccessService;
import ateam.user.service.UserService;
import ateam.validator.Validator;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/user")
@RegisterProvider(ValidatorExceptionMapper.class)
@RegisterProvider(UnauthorizedExceptionMapper.class)
@RegisterProvider(ConflictExceptionMapper.class)
@Singleton
public class UserEndpoint {

	@Inject
	private UserService userService;

	@Inject
	private AccessService accessService;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCurrentUser(@HeaderParam("Authorization") UUID loginId) throws UnauthorizedException {
		Integer userId = accessService.getUserIdIfLoggedIn(loginId);
		if(userId == null)
			throw new UnauthorizedException("Not logged in!");
		User user = userService.loadUser(userId);
		if(user == null)
			throw new UnauthorizedException("Not logged in!");
		return Response.ok(user).build();

	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(@RequestBody(required = true) User user) {
		user.setUserId(0);
		user.setCreateAt(null);
		Validator.validate(user);
		user = userService.createUser(user);
		return Response.ok(user).build();
	}

	@PUT
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@RequestBody(required = true) User user, @PathParam("userId") int userId) {
		user.setUserId(userId);
		Validator.validate(user);
		user = userService.updateUser(user);
		return Response.ok(user).build();
	}

}
