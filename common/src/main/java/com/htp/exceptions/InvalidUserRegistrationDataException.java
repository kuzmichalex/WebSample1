package com.htp.exceptions;

public class InvalidUserRegistrationDataException extends RuntimeException{
	public InvalidUserRegistrationDataException(String message) {
		super(message);
	}
}
