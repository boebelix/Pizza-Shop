package ateam.user.exceptionmapper;

import ateam.model.ExceptionResponse;
import ateam.user.model.exception.UserServiceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Date;

public class UserServiceExceptionMapper  implements ExceptionMapper<UserServiceException> {
	@Override
	public Response toResponse(UserServiceException e) {
		System.out.println("UserServiceException! Returning code 500. Message: " + e.getMessage());
		e.printStackTrace();
		ExceptionResponse response = new ExceptionResponse();
		response.setStatus(500);
		response.setTimestamp(new Date());
		response.setMessage(e.getMessage());
		return Response.status(500).entity(response).build();
	}
}
