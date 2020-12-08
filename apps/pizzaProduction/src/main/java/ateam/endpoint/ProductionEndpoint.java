package ateam.endpoint;

import ateam.exceptionmapper.ShopExceptionMapper;
import ateam.exceptionmapper.UnknownEntityExceptionMapper;
import ateam.exceptionmapper.ValidationExceptionMapper;
import ateam.model.OrderProducer;
import ateam.model.entity.Order;
import ateam.model.exception.UnknownEntityException;

import ateam.validator.Validator;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/production")
@RegisterProvider(ValidationExceptionMapper.class)
@RegisterProvider(UnknownEntityExceptionMapper.class)
@RegisterProvider(ShopExceptionMapper.class)
@Singleton
public class ProductionEndpoint {

	@Inject
	OrderProducer producer;

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response produceOrder(@RequestBody(required = true)Order order, int uuid) throws UnknownEntityException {

		Validator.validate(order);

		if(producer.produceOrder(order,uuid))
			return Response.status(Response.Status.OK.getStatusCode()).build();

		return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).build();
	}
}
