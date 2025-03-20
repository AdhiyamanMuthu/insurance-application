package com.insurance.aggregator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	public ResponseEntity<ErrorInfo> handle(Exception e){
		ErrorInfo info = ErrorInfo.builder().errorMessage(e.getMessage()).errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(info);
	}
}
