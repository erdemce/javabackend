package com.erdem.enowa.customexception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BaumNotFoundException.class)
	public ResponseEntity<Object> handleBaumNotFoundException(BaumNotFoundException e, WebRequest request){
		
		Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(StrasseNotFoundException.class)
	public ResponseEntity<Object> handleStrasseNotFoundException(StrasseNotFoundException e, WebRequest request){
		
		Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> restExceptionHandler(Exception e, WebRequest request){
		
		Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "oops..Es gibt einen Fehler...");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		
	}
}
