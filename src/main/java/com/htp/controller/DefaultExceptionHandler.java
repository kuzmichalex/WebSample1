//security
package com.htp.controller;

import com.htp.controller.response.ErrorMessage;
import com.htp.exceptions.EntityNotFoundException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {

	/*Ловим ошибки обусловленные  несоответствием состава реквизитов в jSon*/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(1000L, e.getLocalizedMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(1001L, e.getLocalizedMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException e){
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(2000L, e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleUserNotFoundException(EntityNotFoundException e){
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(2000L, e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<ErrorMessage> handleMalformedJwtException(MalformedJwtException e){
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(3000L, e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
	}

	/* Хибернейтовские исключения по*/
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException e){
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(3000L, e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
	}

	/* Исключения валидации path variable */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException e){
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(3001L, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
	}



	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
		/* Handles all other exceptions. Status code 500. */
		log.error(e.getMessage(), e);
		log.info(e.getMessage(), e);
		System.err.println(e.getMessage());
		System.err.println(e.getClass().getName());

		return new ResponseEntity<>(new ErrorMessage(e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
