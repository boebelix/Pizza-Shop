package ateam.exceptionmapper;

import ateam.model.exception.UnknownEntityException;
import ateam.util.ExceptionMapperUtils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnknownEntityExceptionMapper implements ExceptionMapper<UnknownEntityException> {
	@Override
	public Response toResponse(UnknownEntityException e) {
		System.out.println("UnknownEntityException! Returning code 404. Message: " + e.getMessage());
		return ExceptionMapperUtils.getResponse(e.getMessage(), Response.Status.NOT_FOUND);
	}
}
