package ateam.client.procurement;

import ateam.model.entity.ProcurementLog;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/procurement")
public interface ProcurementClient {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	Response createProcurementLog(ProcurementLog log) throws IOException;
}
