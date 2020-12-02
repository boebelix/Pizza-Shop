package ateam.exceptionmapper;

import ateam.model.exception.ExceptionResponse;
import ateam.model.exception.UnknownEntityException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class UnknownEntityExceptionMapper implements ExceptionMapper<UnknownEntityException> {
	@Override
	public Response toResponse(UnknownEntityException e) {
		System.out.println("UnknownEntityException! Returning code 404. Message: " + e.getMessage());
		ExceptionResponse response = new ExceptionResponse();
		response.setStatus(Response.Status.NOT_FOUND.getStatusCode());
		response.setTimestamp(new Date());
		response.setMessage(e.getMessage());
		return Response.status(Response.Status.NOT_FOUND).entity(response).build();
	}
}
