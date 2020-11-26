package ateam.client.user;

import ateam.client.mapper.UnauthorizedExceptionResponseMapper;
import ateam.client.mapper.UserServiceExceptionResponseMapper;
import ateam.client.mapper.ValidatorExceptionResponseMapper;
import ateam.model.exception.UnauthorizedException;
import ateam.model.exception.UserServiceException;
import ateam.model.request.LoginData;
import ateam.model.response.LoginResponse;
import ateam.validator.ValidationException;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

import javax.ws.rs.*;
import java.util.UUID;

@Path("/auth")
@Consumes("application/json")
@RegisterProvider(UnauthorizedExceptionResponseMapper.class)
@RegisterProvider(ValidatorExceptionResponseMapper.class)
@RegisterProvider(UserServiceExceptionResponseMapper.class)
public interface AuthClient {

	@POST
	LoginResponse loginUser(LoginData loginData) throws ValidationException, UnauthorizedException, UserServiceException;

	@DELETE
	void logoutUser(@HeaderParam("Authorization") UUID loginId);

}
