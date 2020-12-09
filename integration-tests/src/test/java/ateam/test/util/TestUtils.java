package ateam.test.util;

import ateam.user.endpoints.UserEndpoint;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

	public static <T> T setupClient(final String uri, final Class<? extends T> clazz) {
		List<Class<?>> providers = new ArrayList<>();
		providers.add(JsonBProvider.class);
		return JAXRSClientFactory.create(uri, clazz, providers);
	}

}
