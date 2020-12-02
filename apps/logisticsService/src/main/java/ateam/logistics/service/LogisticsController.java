package ateam.logistics.service;

import ateam.client.user.UserInternalClient;
import ateam.exceptionmapper.UnauthorizedExceptionMapper;
import ateam.exceptionmapper.ValidatorExceptionMapper;
import ateam.logistics.model.LogisticsLog;
import ateam.model.entity.LogisticsPostInput;
import ateam.model.entity.User;
import ateam.model.exception.UnauthorizedException;
import ateam.util.LogService;
import ateam.validator.ValidationException;
import ateam.validator.Validator;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

import javax.inject.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/delivery")
@Singleton
@RegisterProvider(ValidatorExceptionMapper.class)
@RegisterProvider(UnauthorizedExceptionMapper.class)
public class LogisticsController {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createLogisticsLog(LogisticsPostInput input) throws IOException, URISyntaxException, UnauthorizedException, NamingException, ValidationException {
		Validator.validate(input);
		URI userURI = new URI(InitialContext.doLookup("UserInternURI"));
		UserInternalClient userInternalClient = RestClientBuilder.newBuilder()
			.baseUri(userURI)
			.build(UserInternalClient.class);
		User user = userInternalClient.getUser(input.getUserId(), InitialContext.doLookup("appServicePassword"));

		LogisticsLog log = new LogisticsLog(user);
		LogService logService = new LogService(InitialContext.doLookup("LogisticsLogPath"));
		boolean writeSuccessful = logService.log(log);
		Validator.validate(log);

		if (writeSuccessful) {
			return Response.status(Response.Status.CREATED.getStatusCode()).build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "write to Log not successful").build();
		}

	}
}
