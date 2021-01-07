package ateam.shop.service;

import ateam.model.entity.Size;
import ateam.model.entity.Topping;
import ateam.shop.db.DBManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class PizzaService {

	@Inject
	private DBManager dbManager;

	public List<Topping> getToppings() {
		return dbManager.getToppings();
	}

	public List<Size> getSizes() {
		return dbManager.getSizes();
	}
}
