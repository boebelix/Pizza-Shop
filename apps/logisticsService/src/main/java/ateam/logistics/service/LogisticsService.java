package ateam.logistics.service;

import ateam.client.user.UserInternalClient;
import ateam.logistics.model.LogisticsLog;
import ateam.model.entity.LogisticsPostInput;
import ateam.model.entity.User;
import ateam.model.exception.UnauthorizedException;
import ateam.util.LogService;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/delivery")
@Singleton
public class LogisticsService {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createLogisticsLog(LogisticsPostInput input) throws IOException, URISyntaxException, UnauthorizedException {
		boolean inputValid = input.checkValid();
		if (!inputValid) {
			return Response.status(400, "invalid data").build();
		}
		URI userURI = new URI("http://localhost:9080/user_intern/");
		UserInternalClient userInternalClient = RestClientBuilder.newBuilder()
			.baseUri(userURI)
			.build(UserInternalClient.class);
		User user = userInternalClient.getUser(input.getUserId(), "ferEG42gs4tgdsGFDS"); //import password from server.xml

		LogisticsLog log = new LogisticsLog(user);
		LogService logService = new LogService("logistics.log");
		boolean writeSuccessful = logService.log(log);
		boolean valid = log.checkValid();

		if (writeSuccessful && valid) {
			return Response.status(201).build();
		} else if (!writeSuccessful) {
			return Response.status(500, "write to Log not successful").build();
		} else {
			return Response.status(501, "logdata invalid").build();
		}

	}
}
