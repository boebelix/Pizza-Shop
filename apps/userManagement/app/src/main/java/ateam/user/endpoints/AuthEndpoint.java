package ateam.user.endpoints;

import ateam.exceptionmapper.UnauthorizedExceptionMapper;
import ateam.exceptionmapper.UserServiceExceptionMapper;
import ateam.exceptionmapper.ValidatorExceptionMapper;
import ateam.model.entity.User;
import ateam.model.exception.UnauthorizedException;
import ateam.model.request.LoginData;
import ateam.model.response.LoginResponse;
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

@Path("/auth")
@RegisterProvider(ValidatorExceptionMapper.class)
@RegisterProvider(UnauthorizedExceptionMapper.class)
@RegisterProvider(UserServiceExceptionMapper.class)
@Singleton
public class AuthEndpoint {

	@Inject
	private UserService userService;

	@Inject
	private AccessService accessService;


	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginUser(@RequestBody(required = true) LoginData loginData) throws UnauthorizedException {
		Validator.validate(loginData);
		UUID loginId = accessService.login(loginData);
		int userId = accessService.getUserIdIfLoggedIn(loginId);
		User user = userService.loadUser(userId);
		LoginResponse loginResponse = new LoginResponse(loginId, user);
		return Response.ok(loginResponse).build();
	}

	@DELETE
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response logoutUser(@HeaderParam("Authorization") UUID loginId) {
		if(loginId != null) {
			accessService.logout(loginId);
		}
		return Response.ok().build();
	}

}
