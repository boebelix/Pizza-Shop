package ateam.logistics.service;

import ateam.client.user.UserInternalClient;
import ateam.exceptionmapper.LogServiceExceptionMapper;
import ateam.exceptionmapper.UnauthorizedExceptionMapper;
import ateam.exceptionmapper.ValidationExceptionMapper;
import ateam.logistics.model.LogisticsLog;
import ateam.model.entity.LogisticsPostInput;
import ateam.model.entity.User;
import ateam.model.exception.UnauthorizedException;
import ateam.util.LogService;
import ateam.util.LogServiceException;
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
@RegisterProvider(ValidationExceptionMapper.class)
@RegisterProvider(UnauthorizedExceptionMapper.class)
@RegisterProvider(LogServiceExceptionMapper.class)
public class LogisticsController {

	private final LogService logService;
	private URI userInternURI;
	private String userInternPW;

	public LogisticsController() throws NamingException, LogServiceException, URISyntaxException {
		this.logService = new LogService(InitialContext.doLookup("procurementLogPath"));
		this.userInternURI = new URI(InitialContext.doLookup("UserInternURI"));
		this.userInternPW = InitialContext.doLookup("appServicePassword");
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createLogisticsLog(LogisticsPostInput input)throws LogServiceException, UnauthorizedException, ValidationException {
		Validator.validate(input);
		UserInternalClient userInternalClient = RestClientBuilder.newBuilder()
			.baseUri(userInternURI)
			.build(UserInternalClient.class);
		User user = userInternalClient.getUser(input.getUserId(), userInternPW);

		LogisticsLog log = new LogisticsLog(user);
		Validator.validate(log);
		logService.log(log);

		return Response.status(Response.Status.CREATED.getStatusCode()).build();
	}
}
