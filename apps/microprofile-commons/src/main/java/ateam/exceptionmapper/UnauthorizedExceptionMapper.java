package ateam.exceptionmapper;

import ateam.model.exception.UnauthorizedException;
import ateam.util.ExceptionMapperUtils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {
	@Override
	public Response toResponse(UnauthorizedException e) {
		System.out.println("UnauthorizedException! Returning code 401. Message: " + e.getMessage());
		return ExceptionMapperUtils.getResponse(e.getMessage(), Response.Status.UNAUTHORIZED);
	}
}
