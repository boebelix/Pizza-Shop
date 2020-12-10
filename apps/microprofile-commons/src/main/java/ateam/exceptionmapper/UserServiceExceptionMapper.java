package ateam.exceptionmapper;

import ateam.model.exception.ExceptionResponse;
import ateam.model.exception.UserServiceException;
import ateam.util.ExceptionMapperUtils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Date;

public class UserServiceExceptionMapper implements ExceptionMapper<UserServiceException> {
	@Override
	public Response toResponse(UserServiceException e) {
		System.out.println("UserServiceException! Returning code 500. Message: " + e.getMessage());
		e.printStackTrace();
		return ExceptionMapperUtils.getResponse(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
	}
}
