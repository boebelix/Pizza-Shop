package ateam.logistics.service;

import ateam.logistics.model.LogisticsLog;
import ateam.logistics.model.LogisticsPostInput;
import ateam.util.LogService;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/delivery")
@Singleton
public class LogisticsService {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createLogisticsLog(LogisticsPostInput input) throws IOException {
		boolean inputValid = input.checkValid();
		if (!inputValid) {
			return Response.status(400, "invalid data").build();
		}
		// TODO: get request to user
		LogisticsLog log = new LogisticsLog();
		// TODO: fill log with data from get request
		LogService logService = new LogService("logistics.log");
		boolean writeSuccessful = logService.log(log);
		boolean valid = log.checkValid();
		if (writeSuccessful && valid) {
			return Response.status(201).build();
		} else if (!writeSuccessful) {
			return Response.status(500, "write to Log not successful").build();
		} else {
			return Response.status(501, "logdata invalid").build();
		}

	}
}
