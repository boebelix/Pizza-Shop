package ateam.client.user;

import ateam.client.mapper.UnauthorizedExceptionResponseMapper;
import ateam.exceptionmapper.UserServiceExceptionMapper;
import ateam.exceptionmapper.ValidatorExceptionMapper;
import ateam.model.exception.UnauthorizedException;
import ateam.model.exception.UserServiceException;
import ateam.model.request.LoginData;
import ateam.model.response.LoginResponse;
import ateam.validator.ValidationException;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.UUID;

@Path("/auth")
@Consumes("application/json")
@RegisterProvider(UnauthorizedExceptionResponseMapper.class)
@RegisterProvider(ValidatorExceptionMapper.class)
@RegisterProvider(UserServiceExceptionMapper.class)
public interface AuthClient {

	@POST
	LoginResponse loginUser(LoginData loginData) throws ValidationException, UnauthorizedException, UserServiceException;

	@DELETE
	void logoutUser(UUID loginId);

}
