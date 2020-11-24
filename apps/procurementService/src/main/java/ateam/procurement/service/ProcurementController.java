package ateam.procurement.service;

import ateam.model.entity.ProcurementLog;
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
		LogService logService = new LogService("procurement.log");
		boolean writeSuccessful = logService.log(log);
		boolean valid = log.checkValid();
		if (writeSuccessful == true && valid == true) {
			return Response.status(201).build();
		} else if (writeSuccessful == false) {
			return Response.status(500, "Log not succesful").build();
		} else {
			return Response.status(400).build();
		}
	}
}
