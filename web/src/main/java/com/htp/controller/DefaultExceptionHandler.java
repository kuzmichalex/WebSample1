//security
package com.htp.controller;

import com.htp.controller.response.ErrorMessage;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.exceptions.InvalidUserRegistrationDataException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {


	/* Исключения валидации path variable */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(1001L, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
	}

	/*Неправильнгая колонка для сортировки в поиске с пагинацией*/
	@ExceptionHandler(PropertyReferenceException.class)
	public ResponseEntity<ErrorMessage> handlePropertyReferenceException(PropertyReferenceException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(1002L, e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
	}

	/* несовпадения типа. В path variable буквы, а мы наивно цифры ждём! */
	@ExceptionHandler(TypeMismatchException.class)
	public ResponseEntity<ErrorMessage> handleTypeMismatchException(TypeMismatchException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(1003L, "Oh, no! Path variable Type mismatch!"), HttpStatus.BAD_REQUEST);
	}

	/* Что-то не так с ообщением. Вероятно, засули вместо даты непойми что*/
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageConversionException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(1004L, e.getLocalizedMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(1005L, e.getLocalizedMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
	}


	/* Неудачная аутентификация*/
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(2000L, e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
	}

	/* Что-то не так с данными на регистуцию пользователя*/
	@ExceptionHandler(InvalidUserRegistrationDataException.class)
	public ResponseEntity<ErrorMessage> handleTInvalidUserRegistrationDataException(InvalidUserRegistrationDataException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(2001L, "I can't register this dude because: " + e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/*Что-то не так с JWT*/
	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<ErrorMessage> handleMalformedJwtException(MalformedJwtException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(2002L, e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
	}

	/*Пытаемся записать дубликаты по ключу*/
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(9000L, e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
	}

	/*Ничего не нашли*/
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleUserNotFoundException(EntityNotFoundException e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ErrorMessage(9001L, e.getLocalizedMessage()), HttpStatus.NOT_FOUND);
	}

	/* Все прочие ексепшены. Если сработает - значит, где-то недосмотр*/
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
