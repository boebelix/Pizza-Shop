package ateam.model.exception;

public class ShopException extends RuntimeException {

	public ShopException(String message, Throwable e) {
		super(message, e);
	}

	public ShopException(String message) {
		super(message);
	}
}
