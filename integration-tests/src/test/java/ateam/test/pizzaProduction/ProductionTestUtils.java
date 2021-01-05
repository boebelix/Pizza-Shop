package ateam.test.pizzaProduction;

import ateam.model.entity.*;

public class ProductionTestUtils {
	public static ShopProductionItem createDefaultOrder() {
		ShopProductionItem item=new ShopProductionItem();
		Pizza testPizza=new Pizza();
		testPizza.setId(1);
		testPizza.setOrderId(1);
		testPizza.setSizeId(2);
		Topping topping=new Topping(1,"Salami", 10,"Gramm");

		testPizza.addTopping(topping);

		item.addPizza(testPizza);

		return item;
	}
	public static ShopProductionItem createEmptyOrder()
	{
		return new ShopProductionItem();
	}
}
