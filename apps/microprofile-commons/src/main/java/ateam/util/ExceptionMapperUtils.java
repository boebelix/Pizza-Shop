package ateam.util;

import ateam.model.exception.ExceptionResponse;

import javax.ws.rs.core.Response;
import java.util.Date;

public class ExceptionMapperUtils {
	public static Response getResponse(String message, Response.Status status) {
		ExceptionResponse response = new ExceptionResponse();
		response.setStatus(status.getStatusCode());
		response.setTimestamp(new Date());
		response.setMessage(message);
		return Response.status(status).entity(response).build();
	}
}
