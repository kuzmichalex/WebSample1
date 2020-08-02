package com.htp.exceptions;

public class EntityNotFoundException extends RuntimeException {
	public EntityNotFoundException() {
		super("Entity not found");
	}

	public EntityNotFoundException(String message) {
		super("Entity not found. " + message);
	}

	public static EntityNotFoundException newException(String message) {
		return new EntityNotFoundException(message);
	}
}
