package ateam.production.service;

import ateam.client.procurement.ProcurementClient;
import ateam.model.entity.*;
import ateam.model.exception.ProductionException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class OrderProducer {

	public void produceOrder(ShopProductionItem toProduce) {

		List<ProcurementLogItem> toOrder=new LinkedList<>();

		for(Pizza pizza:toProduce.getItems())
		{
			toOrder.addAll(producePizza(pizza));
		}
		try {
			Thread.sleep(10000);
		}catch(InterruptedException e)
		{
			throw new ProductionException("Production got Interrupted");
		}

		ProcurementLog procurementLog=new ProcurementLog();
		procurementLog.setItems(toOrder);

	}

	private List<ProcurementLogItem> producePizza(Pizza pizza)
	{
		List<ProcurementLogItem> used=new LinkedList<>();

		for(PizzaTopping t:pizza.getToppings())
		{
			ProcurementLogItem item= new ProcurementLogItem();


			used.add(item);
		}

		return used;
	}

}

