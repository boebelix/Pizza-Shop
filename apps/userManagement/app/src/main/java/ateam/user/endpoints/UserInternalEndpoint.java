package ateam.user.endpoints;

import ateam.exceptionmapper.UnauthorizedExceptionMapper;
import ateam.exceptionmapper.UnknownEntityExceptionMapper;
import ateam.exceptionmapper.UserServiceExceptionMapper;
import ateam.model.entity.User;
import ateam.model.exception.UnauthorizedException;
import ateam.model.exception.UnknownEntityException;
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
@RegisterProvider(UnknownEntityExceptionMapper.class)
@RegisterProvider(UnauthorizedExceptionMapper.class)
@Singleton
public class UserInternalEndpoint {

	@Inject
	private UserService userService;

	@GET
	@Path("{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@HeaderParam("Authorization") String servicePassword, @PathParam("userid") int userId) throws UnauthorizedException, NamingException {
		if(!Objects.equals(InitialContext.doLookup("appServicePassword"), servicePassword)) {
			throw new UnauthorizedException("ServicePassword wrong!");
		}
		User user = userService.loadUser(userId);
		if(user == null) {
			throw new UnknownEntityException("User existiert nicht!");
		}

		return Response.ok().entity(user).build();
	}

}
