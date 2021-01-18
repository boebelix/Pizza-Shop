package ateam.model.exception;

public class ProductionException extends RuntimeException {

	public ProductionException(String message, Throwable e) {
		super(message, e);
	}

	public ProductionException(String message) {
		super(message);
	}
}

