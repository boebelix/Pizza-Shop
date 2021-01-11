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
public class OrderProducer {

	public void produceOrder(ShopProductionItem toProduce) {

		List<ProcurementLogItem> toOrder=new LinkedList<>();

		ProcurementLogItem dough= new ProcurementLogItem();

		dough.setName("dough");
		dough.setUnit("g");

		int doughAmount=0;

		for(PizzaRestEntity pizza:toProduce.getItems())
		{
			toOrder.addAll(producePizza(pizza));

		}

		toOrder.add(dough);

		try {
			Thread.sleep(10000);
		}catch(InterruptedException e)
		{
			System.out.println("interrupt");
			throw new ProductionException("Production got Interrupted");
		}


		ProcurementLog procurementLog=new ProcurementLog();
		procurementLog.setItems(toOrder);
		ProcurementClient procurement;
		try {
			procurement = RestClientBuilder
				.newBuilder().baseUri(new URI(InitialContext.doLookup("procurementInternURI"))).build(ProcurementClient.class);

			procurement.createProcurementLog(procurementLog);
		}
		catch (Exception e)
		{
			System.out.println("logpath");
			throw new ProductionException("couldn't find procurementLogPath: "+ e.getMessage(),e);
		}

		LogisticsPostInput logisticInput= new LogisticsPostInput();
		logisticInput.setOrderId(toProduce.getOrderID());
		logisticInput.setUserId(toProduce.getUserID());
		LogisticsClient logistics;
		try {
			logistics = RestClientBuilder
				.newBuilder().baseUri(new URI(InitialContext.doLookup("LogisticsInternURI"))).build(LogisticsClient.class);

			logistics.createLogisticsLog(logisticInput);
		}
		catch (Exception e)
		{
			System.out.println("internuri");
			throw new ProductionException("couldn't find LogisticsInternURI: "+ e.getMessage(),e);
		}


	}

	private List<ProcurementLogItem> producePizza(PizzaRestEntity pizza)
	{
		List<ProcurementLogItem> used=new LinkedList<>();

		for(Topping t:pizza.getToppings())
		{
			ProcurementLogItem item= new ProcurementLogItem();

			item.setAmount((int)(t.getBaseAmount() * pizza.getToppingFactor()));
			item.setUnit(t.getUnit());
			item.setName(t.getName());
			used.add(item);
		}

		return used;
	}

	public OrderProducer() {
	}
}

