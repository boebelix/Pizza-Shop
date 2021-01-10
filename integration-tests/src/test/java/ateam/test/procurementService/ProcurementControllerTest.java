package ateam.test.procurementService;

import ateam.client.procurement.ProcurementClient;
import ateam.model.entity.ProcurementLog;
import ateam.model.entity.ProcurementLogItem;
import ateam.test.util.TestConstants;
import ateam.test.util.TestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProcurementControllerTest {
	private static ProcurementClient procurementClient;

	@BeforeAll
	public static void setupClass() {
		procurementClient = TestUtils.setupClient(TestConstants.PROCUREMENT_SERVICE_URI, ProcurementClient.class);
	}

	@Test
	void createProcurementLog() throws IOException {
		ProcurementLog log = new ProcurementLog();
		ArrayList<ProcurementLogItem> logItems = new ArrayList<>();
		ProcurementLogItem logItem = new ProcurementLogItem();
		logItem.setName("Salami");
		logItem.setAmount(5);
		logItem.setUnit("gramm");

		logItems.add(logItem);
		log.setItems(logItems);

		Response response = procurementClient.createProcurementLog(log);

		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
	}
}
