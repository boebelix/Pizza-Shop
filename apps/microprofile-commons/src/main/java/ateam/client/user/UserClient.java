package ateam.client.user;

import ateam.client.mapper.ConflictExceptionResponseMapper;
import ateam.client.mapper.UnauthorizedExceptionResponseMapper;
import ateam.exceptionmapper.UserServiceExceptionMapper;
import ateam.exceptionmapper.ValidatorExceptionMapper;
import ateam.model.entity.User;
import ateam.model.exception.ConflictException;
import ateam.model.exception.UnauthorizedException;
import ateam.model.exception.UserServiceException;
import ateam.validator.ValidationException;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.UUID;

@Path("/user")
@Consumes("application/json")
@RegisterRestClient
@RegisterProvider(UnauthorizedExceptionResponseMapper.class)
@RegisterProvider(ConflictExceptionResponseMapper.class)
@RegisterProvider(ValidatorExceptionMapper.class)
@RegisterProvider(UserServiceExceptionMapper.class)
public interface UserClient {

	@POST
	User createUser(User user) throws ConflictException, ValidationException, UserServiceException;

	@GET
	User getCurrentUser(UUID loginId) throws UnauthorizedException, UserServiceException;

}
