package ateam.client.user;

import ateam.client.mapper.UnauthorizedExceptionResponseMapper;
import ateam.client.mapper.UnknownEntityExceptionMapper;
import ateam.exceptionmapper.UserServiceExceptionMapper;
import ateam.model.entity.User;
import ateam.model.exception.UnauthorizedException;
import ateam.model.exception.UnknownEntityException;
import ateam.model.exception.UserServiceException;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

import javax.ws.rs.*;

@Path("/user_intern")
@Consumes("application/json")
@RegisterProvider(UnauthorizedExceptionResponseMapper.class)
@RegisterProvider(UserServiceExceptionMapper.class)
@RegisterProvider(UnknownEntityExceptionMapper.class)
public interface UserInternalClient {

	@GET()
	@Path("{userid}")
	User getUser(@PathParam("userid") int userId, @HeaderParam("Authorization") String servicePassword) throws UserServiceException, UnauthorizedException, UnknownEntityException;

}
