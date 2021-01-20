package ateam.test.pizzaProduction;

import ateam.model.entity.*;
import ateam.test.userService.model.UserRequest;

import java.util.LinkedList;
import java.util.List;

public class ProductionTestUtils {
	public static ShopProductionItem createDefaultOrder(int userId) {
		ShopProductionItem item=new ShopProductionItem();
		item.setOrderID(1);
		item.setUserID(userId);
		List<Topping> toppings=new LinkedList<>();

		toppings.add(new Topping(1,"Salami", 10,"g"));

		PizzaRestEntity testPizza=new PizzaRestEntity(1,300,1.5,toppings);

		//Topping topping=new Topping(1,"Salami", 10,"Gramm");

		item.addPizza(testPizza);

		System.out.println("test item looks like this: "+ item);
		return item;
	}

	public static ShopProductionItem createEmptyOrder()
	{
		return new ShopProductionItem();
	}
}
