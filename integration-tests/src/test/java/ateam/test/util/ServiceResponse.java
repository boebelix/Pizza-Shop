package ateam.test.util;


import ateam.model.exception.ExceptionResponse;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ServiceResponse<T> {

	private static final List<Integer> OK_CODES = Arrays.asList(
		Response.Status.OK.getStatusCode(),
		Response.Status.CREATED.getStatusCode(),
		Response.Status.ACCEPTED.getStatusCode(),
		Response.Status.NO_CONTENT.getStatusCode()
	);

	private final T entity;
	private final ExceptionResponse errorEntity;
	private final Response response;

	private ServiceResponse(final Response response, final Class<T> clazz) {
		this.response = response;

		if (OK_CODES.contains(response.getStatus())) {
			entity = response.readEntity(clazz);
			errorEntity = null;
		} else {
			entity = null;
			errorEntity = response.readEntity(ExceptionResponse.class);
		}
	}

	public static <T> ServiceResponse<T> parse(final Response response, final Class<T> clazz) {
		return new ServiceResponse<>(response, clazz);
	}

	public Optional<T> getResponseEntity() {
		return Optional.ofNullable(entity);
	}

	public Optional<ExceptionResponse> getErrorEntity() {
		return Optional.ofNullable(errorEntity);
	}

	public boolean hasError() {
		return errorEntity != null;
	}

}
