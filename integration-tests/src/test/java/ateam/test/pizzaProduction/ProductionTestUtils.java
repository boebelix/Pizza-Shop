package ateam.test.pizzaProduction;

import ateam.model.entity.*;
import ateam.test.userService.model.UserRequest;

public class ProductionTestUtils {
	public static ShopProductionItem createDefaultOrder() {
		ShopProductionItem item=new ShopProductionItem();
		Pizza testPizza=new Pizza();
		testPizza.setID(1);
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