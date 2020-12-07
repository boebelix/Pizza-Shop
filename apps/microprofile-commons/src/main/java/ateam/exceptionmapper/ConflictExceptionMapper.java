package ateam.exceptionmapper;

import ateam.model.exception.ConflictException;
import ateam.model.exception.ExceptionResponse;
import ateam.util.ExceptionMapperUtils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class ConflictExceptionMapper implements ExceptionMapper<ConflictException> {

	@Override
	public Response toResponse(ConflictException e) {
		System.out.println("ConflictException! Returning code 409. Message: " + e.getMessage());

		return ExceptionMapperUtils.getResponse(e.getMessage(), Response.Status.CONFLICT);
	}
}
