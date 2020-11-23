package ateam.procurement.service;

import ateam.procurement.model.ProcurementLog;
import ateam.util.LogService;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/procurement")
@Singleton
public class ProcurementController {
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProcurementLog(ProcurementLog log) throws IOException {
		System.out.println(log.toString());
		LogService logService=new LogService("procurement.log");
		logService.log(log);
		return Response.ok().build();
	}
}
