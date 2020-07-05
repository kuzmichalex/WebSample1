package com.htp.exceptions;

public class UserNameNotFoundException extends RuntimeException {
	public UserNameNotFoundException(String message) {
		super(message);
	}
}
