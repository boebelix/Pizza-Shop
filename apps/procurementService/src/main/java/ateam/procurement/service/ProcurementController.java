package ateam.procurement.service;

import ateam.exceptionmapper.LogServiceExceptionMapper;
import ateam.exceptionmapper.ValidationExceptionMapper;
import ateam.model.entity.ProcurementLog;
import ateam.util.LogService;
import ateam.util.LogServiceException;
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

@Path("/procurement")
@RegisterProvider(ValidationExceptionMapper.class)
@RegisterProvider(LogServiceExceptionMapper.class)
@Singleton
public class ProcurementController {

	private final LogService logService;

	public ProcurementController() throws NamingException, LogServiceException {
		this.logService = new LogService(InitialContext.doLookup("procurementLogPath"));
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProcurementLog(ProcurementLog log) throws ValidationException, LogServiceException {
		Validator.validate(log);
		System.out.println(log.toString());

		logService.log(log);
		return Response.status(Response.Status.CREATED.getStatusCode()).build();
	}
}
