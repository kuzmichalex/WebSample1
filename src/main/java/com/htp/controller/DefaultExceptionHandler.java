//security
package com.htp.controller;

import com.htp.controller.response.ErrorMessage;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(1L, e.getLocalizedMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException e){
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(2L, e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<ErrorMessage> handleMalformedJwtException(MalformedJwtException e){
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(3L, e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
		/* Handles all other exceptions. Status code 500. */
		log.error(e.getMessage(), e);
		log.info(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
