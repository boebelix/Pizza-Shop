package ateam.exceptionmapper;


import ateam.model.exception.ExceptionResponse;
import ateam.util.ExceptionMapperUtils;
import ateam.util.LogServiceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class LogServiceExceptionMapper implements ExceptionMapper<LogServiceException> {
	@Override
	public Response toResponse(LogServiceException e) {
		System.out.println("LogServiceException! returning code 500. Message: "+ e.getMessage());
		return ExceptionMapperUtils.getResponse(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
	}

}
