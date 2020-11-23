package ateam.model.exception;

public class ConflictException extends IllegalArgumentException {

	public ConflictException(String message) {
		super(message);
	}

	public ConflictException(String message, Throwable e) {
		super(message, e);
	}

}
