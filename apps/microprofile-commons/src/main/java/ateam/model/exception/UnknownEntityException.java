package ateam.model.exception;

public class UnknownEntityException extends IllegalArgumentException {

	public UnknownEntityException() {}

    public UnknownEntityException(String s) {
    	super(s);
    }
}
