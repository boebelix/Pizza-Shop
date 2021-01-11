package ateam.client.production;

import ateam.client.mapper.LogServiceExceptionResponseMapper;
import ateam.client.mapper.PizzaProductionExceptionMapper;
import ateam.client.mapper.ValidationExceptionResponseMapper;
import ateam.model.entity.ProcurementLog;
import ateam.model.entity.ShopProductionItem;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/production")
@RegisterRestClient
@RegisterProvider(ValidationExceptionResponseMapper.class)
@RegisterProvider(PizzaProductionExceptionMapper.class)

public interface ProductionClient {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response produceOrder(ShopProductionItem toProduce) throws IOException;
}
