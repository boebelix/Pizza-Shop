package ateam.model;

public class UnauthorizedException extends IllegalAccessException {

	public UnauthorizedException(String message) {
		super(message);
	}
}
