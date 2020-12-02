package ateam.client.user;

import ateam.client.mapper.*;
import ateam.model.entity.User;
import ateam.model.exception.ConflictException;
import ateam.model.exception.UnauthorizedException;
import ateam.model.exception.UnknownEntityException;
import ateam.model.exception.UserServiceException;
import ateam.validator.ValidationException;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sun.security.validator.ValidatorException;

import javax.ws.rs.*;
import java.util.UUID;

@Path("/user")
@Consumes("application/json")
@RegisterRestClient
@RegisterProvider(UnauthorizedExceptionResponseMapper.class)
@RegisterProvider(ConflictExceptionResponseMapper.class)
@RegisterProvider(ValidatorExceptionResponseMapper.class)
@RegisterProvider(UserServiceExceptionResponseMapper.class)
@RegisterProvider(UnknownEntityExceptionResponseMapper.class)
public interface UserClient {

	@POST
	User createUser(User user) throws ConflictException, ValidationException, UserServiceException;

	@GET
	User getCurrentUser(@HeaderParam("Authorization") UUID loginId) throws UnauthorizedException, UserServiceException;

	@PUT
	@Path("/{userId}")
	User updateUser(@HeaderParam("Authorization") UUID loginId, @PathParam("userId") int userId, User user) throws
		ValidatorException, UnknownEntityException, UserServiceException, ConflictException, UnauthorizedException;

}
