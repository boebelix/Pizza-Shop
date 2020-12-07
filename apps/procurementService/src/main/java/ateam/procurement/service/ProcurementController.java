package ateam.procurement.service;

import ateam.exceptionmapper.ValidationExceptionMapper;
import ateam.model.entity.ProcurementLog;
import ateam.util.LogService;
import ateam.validator.ValidationException;
import ateam.validator.Validator;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

import javax.inject.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/procurement")
@RegisterProvider(ValidationExceptionMapper.class)
@Singleton
public class ProcurementController {
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProcurementLog(ProcurementLog log) throws IOException, ValidationException, NamingException {
		Validator.validate(log);
		System.out.println(log.toString());
		LogService logService = new LogService(InitialContext.doLookup("procurementLogPath"));
		boolean writeSuccessful = logService.log(log);
		if (writeSuccessful) {
			return Response.status(Response.Status.CREATED.getStatusCode()).build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Log not succesful").build();
		}
	}
}
