package ateam.client.logistics;

import ateam.client.mapper.UnauthorizedExceptionResponseMapper;
import ateam.client.mapper.ValidationExceptionResponseMapper;
import ateam.model.entity.LogisticsPostInput;
import ateam.model.exception.UnauthorizedException;
import ateam.validator.ValidationException;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;

@Path("/delivery")
@RegisterRestClient
@RegisterProvider(ValidationExceptionResponseMapper.class)
@RegisterProvider(UnauthorizedExceptionResponseMapper.class)
public interface LogisticsClient {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	Response createLogisticsLog(LogisticsPostInput input) throws IOException, URISyntaxException, UnauthorizedException, NamingException, ValidationException;
}
