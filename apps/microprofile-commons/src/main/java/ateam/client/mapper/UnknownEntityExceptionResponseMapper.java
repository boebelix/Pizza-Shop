package ateam.client.mapper;

import ateam.model.exception.UnknownEntityException;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class UnknownEntityExceptionResponseMapper implements ResponseExceptionMapper<UnknownEntityException> {

	@Override
	public boolean handles(int statusCode, MultivaluedMap<String, Object> headers) {
		return statusCode == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
	}

	@Override
	public UnknownEntityException toThrowable(Response response) {
		return new UnknownEntityException();
	}
}
