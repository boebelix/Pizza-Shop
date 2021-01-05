package ateam.model.exception;

public class PizzaShopException extends RuntimeException {

	public PizzaShopException(String message, Throwable e) {
		super(message, e);
	}

	public PizzaShopException(String message) {
		super(message);
	}

}
