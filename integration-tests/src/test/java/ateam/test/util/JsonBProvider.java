package ateam.test.util;


import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Scanner;

@Provider
@Produces({ "*/*" })
@Consumes({ "*/*" })
public class JsonBProvider implements MessageBodyWriter<Object>, MessageBodyReader<Object> {

	private static final Jsonb jsonb = JsonbBuilder.create();
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonBProvider.class);

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public Object readFrom(Class<Object> clazz, Type genericType, Annotation[] annotations,
						   MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
		String stringResult = convertStreamToString(entityStream);
		LOGGER.info(() -> "Response from server: " + stringResult);
		return jsonb.fromJson(stringResult, genericType);
	}

	@SuppressWarnings("resource")
	private static String convertStreamToString(java.io.InputStream is) {
		try (Scanner s = new Scanner(is).useDelimiter("\\A")) {
			return s.hasNext() ? s.next() : "";
		}
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public void writeTo(Object obj, Class<?> type, Type genericType, Annotation[] annotations,
						MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
		String strData = jsonb.toJson(obj);
		LOGGER.info(() -> "Sending data to server: " + strData);
		jsonb.toJson(obj, entityStream);
	}
}
