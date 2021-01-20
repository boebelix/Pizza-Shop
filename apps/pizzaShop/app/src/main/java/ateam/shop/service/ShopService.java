package ateam.shop.service;

import ateam.model.entity.*;
import ateam.shop.db.DBManager;
import ateam.client.production.ProductionClient;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Singleton
public class ShopService {

	@Inject
	private DBManager dbManager;

	@Inject
	private ProductionClient productionClient;

	public Order placeOrder(Order order, int userId) {
		order.setOrderDate(new Date(new GregorianCalendar().getTime().getTime()));
		Order created = dbManager.placeOrder(order);
		ShopProductionItem prodItem = parseOrderToProductionItem(order, userId);
		try {
			productionClient.produceOrder(prodItem);
		} catch (IOException e) {
		}
		return created;
	}

	public Order getOrderById(int oderId) {
		return dbManager.getOrderById(oderId);
	}


	private ShopProductionItem parseOrderToProductionItem(Order order, int userId) {
		ShopProductionItem prodItem = new ShopProductionItem();
		prodItem.setUserID(userId);
		prodItem.setOrderID(order.getId());
		List<Pizza> pizzaDbitems = order.getPizzas();
		for (Pizza dbPizza : pizzaDbitems) {
			prodItem.addPizza(parseDbPizzaToRestPizza(dbPizza));
		}
		return prodItem;
	}

	private PizzaRestEntity parseDbPizzaToRestPizza(Pizza dbPizza) {
		PizzaRestEntity pr = new PizzaRestEntity();
		pr.setID(dbPizza.getId());
		Size size = dbManager.getSizeById(dbPizza.getSizeId());
		pr.setDoughAmount(size.getDoughAmount());
		pr.setToppingFactor(size.getToppingFactor());
		List<PizzaTopping> toppings = dbPizza.getToppings();
		for (PizzaTopping dbTopping : toppings)
			pr.addTopping(dbManager.getToppingById(dbTopping.getToppingId()));
		return pr;
	}

	public ShopService() throws NamingException, URISyntaxException {
		this.productionClient = RestClientBuilder
			.newBuilder().baseUri(new URI(InitialContext.doLookup("PizzaProductionInternURI"))).build(ProductionClient.class);
	}
}
