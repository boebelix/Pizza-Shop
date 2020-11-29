package ateam.model.exception;

public class UserServiceException extends RuntimeException {

	public UserServiceException(String message, Throwable e) {
		super(message, e);
	}

	public UserServiceException(String message) {
		super(message);
	}

}
