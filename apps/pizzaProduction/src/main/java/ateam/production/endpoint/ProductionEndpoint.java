package ateam.production.endpoint;

import ateam.client.mapper.PizzaProductionExceptionMapper;
import ateam.exceptionmapper.ShopExceptionMapper;
import ateam.exceptionmapper.UnknownEntityExceptionMapper;
import ateam.exceptionmapper.ValidationExceptionMapper;
import ateam.model.exception.ProductionException;
import ateam.production.service.OrderProducer;
import ateam.model.entity.ShopProductionItem;
import ateam.validator.Validator;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/production")
@RegisterProvider(ValidationExceptionMapper.class)
@RegisterProvider(UnknownEntityExceptionMapper.class)
@RegisterProvider(PizzaProductionExceptionMapper.class)
@Singleton
public class ProductionEndpoint {

	@Inject
	OrderProducer producer;

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public Response produceOrder(@RequestBody(required = true) ShopProductionItem toProduce) {
	public Response produceOrder(ShopProductionItem toProduce) {
		Validator.validate(toProduce);
		producer.produceOrder(toProduce);

		return Response.status(Response.Status.OK.getStatusCode()).build();
	}
}
