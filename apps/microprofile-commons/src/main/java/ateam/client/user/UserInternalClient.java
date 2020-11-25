package ateam.client.user;

import ateam.client.mapper.UnknownEntityExceptionResponseMapper;
import ateam.exceptionmapper.UnauthorizedExceptionMapper;
import ateam.exceptionmapper.UserServiceExceptionMapper;
import ateam.model.entity.User;
import ateam.model.exception.UnauthorizedException;
import ateam.model.exception.UnknownEntityException;
import ateam.model.exception.UserServiceException;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

import javax.ws.rs.*;

@Path("/user_intern")
@Consumes("application/json")
@RegisterProvider(UnauthorizedExceptionMapper.class)
@RegisterProvider(UserServiceExceptionMapper.class)
@RegisterProvider(UnknownEntityExceptionResponseMapper.class)
public interface UserInternalClient {

	@GET()
	@Path("{userid}")
	User getUser(@PathParam("userid") int userId, @HeaderParam("Authorization") String servicePassword) throws UserServiceException, UnauthorizedException, UnknownEntityException;

}
