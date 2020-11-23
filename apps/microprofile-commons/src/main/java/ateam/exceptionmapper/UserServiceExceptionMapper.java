package ateam.exceptionmapper;

import ateam.model.exception.ExceptionResponse;
import ateam.model.exception.UserServiceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Date;

public class UserServiceExceptionMapper  implements ExceptionMapper<UserServiceException> {
	@Override
	public Response toResponse(UserServiceException e) {
		System.out.println("UserServiceException! Returning code 500. Message: " + e.getMessage());
		e.printStackTrace();
		ExceptionResponse response = new ExceptionResponse();
		response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		response.setTimestamp(new Date());
		response.setMessage(e.getMessage());
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
	}
}
