package ateam.user.endpoints;

import ateam.exceptionmapper.*;
import ateam.model.entity.User;
import ateam.model.exception.UnauthorizedException;
import ateam.user.service.AccessService;
import ateam.user.service.UserService;
import ateam.validator.ValidationException;
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
@RegisterProvider(ValidationExceptionMapper.class)
@RegisterProvider(UserServiceExceptionMapper.class)
@RegisterProvider(UnauthorizedExceptionMapper.class)
@RegisterProvider(ConflictExceptionMapper.class)
@RegisterProvider(UnknownEntityExceptionMapper.class)
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
			throw new UnauthorizedException("Nicht eingeloggt!");
		User user = userService.loadUser(userId);
		if(user == null)
			throw new UnauthorizedException("Nicht eingeloggt!");
		return Response.ok(user).build();

	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(@RequestBody(required = true) User user) {
		user.setUserId(0);
		user.setCreateAt(null);
		if(user.getPassword() == null) {
			throw new ValidationException("Passwort benötigt!");
		}
		Validator.validate(user);
		user = userService.createUser(user);
		return Response.ok(user).build();
	}

	@PUT
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@HeaderParam("Authorization") UUID loginId,
							   @RequestBody(required = true) User user,
							   @PathParam("userId") int userId) throws UnauthorizedException {
		Integer loggedInUserId = accessService.getUserIdIfLoggedIn(loginId);
		if(loggedInUserId == null || userId != loggedInUserId) {
			throw new UnauthorizedException("Du darfst nicht die Nutzerdaten eines anderen Benutzer bearbeiten!");
		}
		user.setUserId(userId);
		Validator.validate(user, Validator.Required.class);
		user = userService.updateUser(user);
		return Response.ok(user).build();
	}

}
