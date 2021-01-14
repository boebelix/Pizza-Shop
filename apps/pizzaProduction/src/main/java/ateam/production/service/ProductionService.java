package ateam.production.service;

import ateam.client.logistics.LogisticsClient;
import ateam.client.procurement.ProcurementClient;
import ateam.client.user.UserClient;
import ateam.model.entity.*;
import ateam.model.exception.ProductionException;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class ProductionService {

	public void produceOrder(ShopProductionItem toProduce) {
		ProcurementClient procurement;
		ProcurementLog procurementLog = parseProductionItemToProcurementLog(toProduce);

		try {
			procurement = RestClientBuilder
				.newBuilder().baseUri(new URI(InitialContext.doLookup("procurementInternURI"))).build(ProcurementClient.class);

			procurement.createProcurementLog(procurementLog);
		} catch (Exception e) {
			throw new ProductionException("couldn't find procurementLogPath: " + e.getMessage(), e);
		}


		sendLogisticsRequest(toProduce.getOrderID(), toProduce.getUserID());

	}

	private static ProcurementLog parseProductionItemToProcurementLog(ShopProductionItem toProduce) {
		List<ProcurementLogItem> toOrder = new LinkedList<>();
		ProcurementLogItem dough = new ProcurementLogItem();

		dough.setName("dough");
		dough.setUnit("g");

		int doughAmount = 0;

		for (PizzaRestEntity pizza : toProduce.getItems()) {
			toOrder.addAll(producePizza(pizza));

			doughAmount += pizza.getDoughAmount();
		}

		dough.setAmount(doughAmount);
		toOrder.add(dough);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			throw new ProductionException("Production got Interrupted");
		}

		ProcurementLog procurementLog = new ProcurementLog();
		procurementLog.setItems(toOrder);

		return procurementLog;
	}

	private void sendLogisticsRequest(int orderId, int userId) {
		LogisticsPostInput logisticInput = new LogisticsPostInput();
		logisticInput.setOrderId(orderId);
		logisticInput.setUserId(userId);
		LogisticsClient logistics;
		try {
			logistics = RestClientBuilder
				.newBuilder().baseUri(new URI(InitialContext.doLookup("LogisticsInternURI"))).build(LogisticsClient.class);

			logistics.createLogisticsLog(logisticInput);
		} catch (Exception e) {
			throw new ProductionException("couldn't find LogisticsInternURI: " + e.getMessage(), e);
		}
	}

	private void sendProcurementRequest() {

	}

	private static List<ProcurementLogItem> producePizza(PizzaRestEntity pizza) {
		List<ProcurementLogItem> used = new LinkedList<>();

		for (Topping t : pizza.getToppings()) {
			ProcurementLogItem item = new ProcurementLogItem();

			item.setAmount((int) (t.getBaseAmount() * pizza.getToppingFactor()));
			item.setUnit(t.getUnit());
			item.setName(t.getName());
			used.add(item);
		}

		return used;
	}

	public ProductionService() {
	}
}

