package ateam.client.user;

import ateam.client.mapper.UnauthorizedExceptionResponseMapper;
import ateam.client.mapper.UnknownEntityExceptionResponseMapper;
import ateam.client.mapper.UserServiceExceptionResponseMapper;
import ateam.model.entity.User;
import ateam.model.exception.UnauthorizedException;
import ateam.model.exception.UnknownEntityException;
import ateam.model.exception.UserServiceException;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;

@Path("/user_intern")
@Consumes("application/json")
@RegisterRestClient()
@RegisterProvider(UnauthorizedExceptionResponseMapper.class)
@RegisterProvider(UserServiceExceptionResponseMapper.class)
@RegisterProvider(UnknownEntityExceptionResponseMapper.class)
public interface UserInternalClient {

	@GET()
	@Path("{userid}")
	User getUser(@PathParam("userid") int userId, @HeaderParam("Authorization") String servicePassword) throws UserServiceException, UnauthorizedException, UnknownEntityException;

}
