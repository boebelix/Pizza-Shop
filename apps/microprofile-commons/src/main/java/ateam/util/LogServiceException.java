package ateam.util;

import java.io.IOException;

public class LogServiceException extends IOException {
	public LogServiceException(String message, Throwable e) {
		super(message, e);
	}

	public LogServiceException(String message) {
		super(message);
	}
}
