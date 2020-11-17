package ateam.user.endpoints;

import ateam.model.UnauthorizedException;
import ateam.user.exceptionmapper.UserServiceExceptionMapper;
import ateam.user.model.entity.User;
import ateam.user.model.exception.UserServiceException;
import ateam.user.service.UserService;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/user_intern")
@RegisterProvider(UserServiceExceptionMapper.class)
@Singleton
public class UserInternalEndpoint {

	@Inject
	private UserService userService;

	@GET
	@Path("{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@CookieParam("Authorization") String servicePassword, @PathParam("userid") int userId) throws UnauthorizedException, NamingException {
		if(!Objects.equals(InitialContext.doLookup("appServicePassword"), servicePassword)) {
			throw new UnauthorizedException("ServicePassword wrong!");
		}
		User user = userService.loadUser(userId);
		if(user == null)
			throw new UserServiceException("User not found!");

		return Response.ok().entity(user).build();
	}

}
