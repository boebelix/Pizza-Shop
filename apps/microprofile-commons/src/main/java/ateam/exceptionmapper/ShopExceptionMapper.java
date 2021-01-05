package ateam.exceptionmapper;

import ateam.model.exception.ShopException;
import ateam.util.ExceptionMapperUtils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ShopExceptionMapper implements ExceptionMapper<ShopException> {
	@Override
	public Response toResponse(ShopException e) {
		System.out.println("ShopException! Returning code 500. Message: " + e.getMessage());
		return ExceptionMapperUtils.getResponse(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
	}
}
