package ateam.client.mapper;

import ateam.model.exception.ExceptionResponse;
import ateam.model.exception.UnauthorizedException;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class UnauthorizedExceptionResponseMapper implements ResponseExceptionMapper<UnauthorizedException> {

	@Override
	public boolean handles(int statusCode, MultivaluedMap<String, Object> headers) {
		return statusCode == Response.Status.UNAUTHORIZED.getStatusCode();
	}

	@Override
	public UnauthorizedException toThrowable(Response response) {
		if(!response.bufferEntity()) {
			throw new InternalServerErrorException("Another microservice throw an exception and we fucked up parsing it!");
		}
		ExceptionResponse exceptionResponse = (ExceptionResponse) response.getEntity();
		return new UnauthorizedException(exceptionResponse.getMessage());
	}
}
