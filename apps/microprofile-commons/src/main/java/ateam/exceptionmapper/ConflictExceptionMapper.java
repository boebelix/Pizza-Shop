package ateam.exceptionmapper;

import ateam.model.exception.ConflictException;
import ateam.model.exception.ExceptionResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class ConflictExceptionMapper implements ExceptionMapper<ConflictException> {

	@Override
	public Response toResponse(ConflictException e) {
		System.out.println("ConflictException! Returning code 409. Message: " + e.getMessage());

		ExceptionResponse response = new ExceptionResponse();
		response.setStatus(Response.Status.CONFLICT.getStatusCode());
		response.setTimestamp(new Date());
		response.setMessage(e.getMessage());
		return Response.status(Response.Status.CONFLICT).entity(response).build();
	}
}
