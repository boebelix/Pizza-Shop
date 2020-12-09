package ateam.client.procurement;

import ateam.client.mapper.LogServiceExceptionResponseMapper;
import ateam.client.mapper.ValidationExceptionResponseMapper;
import ateam.model.entity.ProcurementLog;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/procurement")
@RegisterRestClient
@RegisterProvider(ValidationExceptionResponseMapper.class)
@RegisterProvider(LogServiceExceptionResponseMapper.class)
public interface ProcurementClient {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	Response createProcurementLog(ProcurementLog log) throws IOException;
}
