package ateam.exceptionmapper;

import ateam.model.exception.ExceptionResponse;
import ateam.model.exception.UnauthorizedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {
	@Override
	public Response toResponse(UnauthorizedException e) {
		System.out.println("UnauthorizedException! Returning code 401. Message: " + e.getMessage());
		ExceptionResponse response = new ExceptionResponse();
		response.setStatus(401);
		response.setTimestamp(new Date());
		response.setMessage(e.getMessage());
		return Response.status(401).entity(response).build();
	}
}
