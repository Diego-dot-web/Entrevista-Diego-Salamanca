package com.entrevista.practice.Ex;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Ex {
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> nullHandler(NullPointerException e) {
		return new ResponseEntity<>("Puntero no encontrado" + e, HttpStatus.FORBIDDEN);


	}

}
