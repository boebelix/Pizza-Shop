package ateam.client.user;

import ateam.client.mapper.UnauthorizedExceptionResponseMapper;
import ateam.model.entity.User;
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
public interface UserClient {

	@POST
	User createUser(User user);

	@GET
	User getCurrentUser(UUID loginId);

}
