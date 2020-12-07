package ateam.exceptionmapper;

import ateam.model.exception.ExceptionResponse;
import ateam.model.exception.ShopException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class ShopExceptionMapper implements ExceptionMapper<ShopException> {
	@Override
	public Response toResponse(ShopException e) {
		System.out.println("ShopException! Returning code 500. Message: " + e.getMessage());
		e.printStackTrace();
		return getResponse(e.getMessage(), e);
	}
}
