package ateam.client.user;

import ateam.model.request.LoginData;
import ateam.model.response.LoginResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.UUID;

@Path("/auth")
@Consumes("application/json")
public interface AuthClient {

	@POST
	LoginResponse loginUser(LoginData loginData);

	@DELETE
	void logoutUser(UUID loginId);

}
