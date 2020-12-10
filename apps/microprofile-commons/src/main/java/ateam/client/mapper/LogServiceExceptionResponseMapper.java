package ateam.client.mapper;

import ateam.model.exception.ExceptionResponse;
import ateam.util.LogServiceException;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class LogServiceExceptionResponseMapper implements ResponseExceptionMapper<LogServiceException> {

	@Override
	public LogServiceException toThrowable(Response response) {
		ExceptionResponse exceptionResponse = (ExceptionResponse) response.getEntity();
		return new LogServiceException(exceptionResponse.getMessage());
	}

	@Override
	public boolean handles(int statusCode, MultivaluedMap<String, Object> headers) {
		return statusCode == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
	}
}
