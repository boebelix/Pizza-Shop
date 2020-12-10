package ateam.exceptionmapper;

import ateam.model.exception.ExceptionResponse;
import ateam.util.ExceptionMapperUtils;
import ateam.validator.ValidationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
	@Override
	public Response toResponse(ValidationException e) {
		System.out.println("ValidationException! Returning code 400. Message: " + e.getMessage());

		return ExceptionMapperUtils.getResponse(e.getMessage(), Response.Status.BAD_REQUEST);
	}
}
