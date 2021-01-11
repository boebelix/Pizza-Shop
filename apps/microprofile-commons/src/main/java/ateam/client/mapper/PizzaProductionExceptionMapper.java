package ateam.client.mapper;

import ateam.model.exception.ProductionException;
import ateam.util.ExceptionMapperUtils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PizzaProductionExceptionMapper implements ExceptionMapper<ProductionException>{
		@Override
		public Response toResponse(ProductionException e) {
			System.out.println("Pizza Production Exception! Message: "+e.getMessage());
			return ExceptionMapperUtils.getResponse(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
