package de.hskl.test;

import ateam.model.entity.User;
import ateam.user.endpoints.UserEndpoint;
import de.hskl.test.util.JsonBProvider;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyFirstUnitTest {

	private static UserEndpoint userEndpoint;

	@BeforeAll
	public static void setupClass() {
		List<Class<?>> providers = new ArrayList<>();
		//providers.add(JsonBindingProvider.class); // trivial implementation of a JAX-RS MessageBodyReader/Writer
		providers.add(JsonBProvider.class);
		String appPath = "http://localhost:9080/";
		userEndpoint = JAXRSClientFactory.create(appPath, UserEndpoint.class, providers);
	}

	@Test
	void addition() throws InterruptedException {

		TimeUnit.SECONDS.sleep(30);

		User toCreate = new User();
		toCreate.setCity("Zweibrücken");
		toCreate.setCountry("Deutschland");
		toCreate.setEmail("test@stud.hs-kl.de");
		toCreate.setNumber("42");
		toCreate.setStreet("Amerikastraße");
		toCreate.setPostalCode("66482");
		toCreate.setFirstName("Max");
		toCreate.setLastName("Mustermann");
		toCreate.setPassword("123456789#!TesT");

		userEndpoint.createUser(toCreate);
	}

}
