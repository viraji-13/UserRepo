package com.deploy.user.exception;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleAllException (Exception ex, WebRequest request){
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(LocalDate.now(), ex.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND)  ;
		
	}
	
	
	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<ExceptionResponse> handleInvalidUserException (InvalidUserException ex, WebRequest request){
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(LocalDate.now(), ex.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND)  ;
		
	}
	
	
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> map = new HashMap<String, String>();
map = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(n->n.getField(), n->n.getDefaultMessage()));
	     return new ResponseEntity<Object>(map , HttpStatus.BAD_REQUEST);
	}
	
}
