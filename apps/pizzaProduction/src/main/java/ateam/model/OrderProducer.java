package ateam.model;

import ateam.model.entity.Order;

import javax.inject.Singleton;

@Singleton
public class OrderProducer {

	public boolean produceOrder(Order order, int uuid)
	{
		return (order.getPizzas()!=null&& !order.getPizzas().isEmpty());
	}
}

