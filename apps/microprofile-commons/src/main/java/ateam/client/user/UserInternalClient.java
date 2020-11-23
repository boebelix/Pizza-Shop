package ateam.client.user;

import ateam.model.entity.User;

import javax.ws.rs.*;

@Path("/user_intern")
@Consumes("application/json")
public interface UserInternalClient {

	@GET()
	@Path("{userid}")
	User getUser(@PathParam("userid") int userId, @HeaderParam("Authorization") String servicePassword);

}
