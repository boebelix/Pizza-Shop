package ateam.test.pizzaProduction;

import ateam.model.entity.*;

import java.util.LinkedList;
import java.util.List;

public class ProductionTestUtils {
	public static ShopProductionItem createDefaultOrder(User testUser) {
		ShopProductionItem item=new ShopProductionItem();
		item.setOrderID(1);
		item.setUserID(testUser.getUserId());
		List<Topping> toppings=new LinkedList<>();

		toppings.add(new Topping(1,"Salami", 10,"g"));

		PizzaRestEntity testPizza=new PizzaRestEntity(1,300,1.5,toppings);

		//Topping topping=new Topping(1,"Salami", 10,"Gramm");

		item.addPizza(testPizza);

		System.out.println("test item looks like this: "+ item);
		return item;
	}

	public static User createUser()
	{
		User user=new User();
		user.setCity("Zweibrücken");
		user.setCountry("Deutschland");
		user.setUsername("PizzaMampfer");
		user.setFirstName("Maria");
		user.setLastName("Musterfrau");
		user.setEmail("mamu99999@stud.hs-kl.de");
		user.setPassword("IcKWIllnEpIzzA444!!11!1!");
		user.setPostalCode("");
		user.setStreet("Am Rosengarten");
		user.setNumber("66482");
		return user;
	}

	public static ShopProductionItem createEmptyOrder()
	{
		return new ShopProductionItem();
	}
}
