package ateam.test.procurementService;

import ateam.model.entity.ProcurementLog;
import ateam.model.entity.ProcurementLogItem;
import ateam.procurement.service.ProcurementController;
import ateam.test.util.TestConstants;
import ateam.test.util.TestUtils;
import ateam.util.LogServiceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProcurementControllerTest {
	private static ProcurementController procurementController;

	@BeforeAll
	public static void setupClass() {
		procurementController = TestUtils.setupClient(TestConstants.PROCUREMENT_SERVICE_URI, ProcurementController.class);
	}

	@Test
	void createProcurementLog() throws LogServiceException {
		ProcurementLog log = new ProcurementLog();
		ArrayList<ProcurementLogItem> logItems = new ArrayList<>();
		ProcurementLogItem logItem = new ProcurementLogItem();
		logItem.setName("Salami");
		logItem.setAmount(5);
		logItem.setUnit("gramm");

		logItems.add(logItem);
		log.setItems(logItems);

		Response response = procurementController.createProcurementLog(log);

		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
	}
}
