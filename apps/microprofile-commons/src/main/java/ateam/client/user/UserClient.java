package ateam.client.user;

import ateam.client.mapper.ConflictExceptionResponseMapper;
import ateam.client.mapper.UnauthorizedExceptionResponseMapper;
import ateam.client.mapper.UserServiceExceptionResponseMapper;
import ateam.client.mapper.ValidatorExceptionResponseMapper;
import ateam.model.entity.User;
import ateam.model.exception.ConflictException;
import ateam.model.exception.UnauthorizedException;
import ateam.model.exception.UserServiceException;
import ateam.validator.ValidationException;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import java.util.UUID;

@Path("/user")
@Consumes("application/json")
@RegisterRestClient
@RegisterProvider(UnauthorizedExceptionResponseMapper.class)
@RegisterProvider(ConflictExceptionResponseMapper.class)
@RegisterProvider(ValidatorExceptionResponseMapper.class)
@RegisterProvider(UserServiceExceptionResponseMapper.class)
public interface UserClient {

	@POST
	User createUser(User user) throws ConflictException, ValidationException, UserServiceException;

	@GET
	User getCurrentUser(@HeaderParam("Authorization") UUID loginId) throws UnauthorizedException, UserServiceException;

}
