package com.epsi.mediatheque.exception.handler;

import com.epsi.mediatheque.data.MediaApiStatus;
import com.epsi.mediatheque.dto.ErrorResponse;
import com.epsi.mediatheque.exception.MediaNotFoundException;
import com.epsi.mediatheque.exception.MedialApiException;
import com.epsi.mediatheque.exception.UnavailablemediaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorResponse> throwableHandler(Throwable exception) {
		log.error(exception.getMessage(), exception);
		ErrorResponse res = new ErrorResponse(MediaApiStatus.MEDIA_API_STATUS_1500.toString(), MediaApiStatus.MEDIA_API_STATUS_1500.getReason());
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MedialApiException.class)
	public ResponseEntity<ErrorResponse> dafReportPortalApiExceptionHandler(MedialApiException exception) {
		ErrorResponse res = new ErrorResponse(exception.getCode(), exception.getMessage());
		return new ResponseEntity<>(res, exception.getStatus());
	}
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ResponseEntity<ErrorResponse> processValidationError(HttpRequestMethodNotSupportedException ex) {
		ErrorResponse res = new ErrorResponse(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), ex.getMessage());
		return new ResponseEntity<>(res, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(MediaNotFoundException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<ErrorResponse> processValidationError(MediaNotFoundException ex) {

		ErrorResponse res = new ErrorResponse(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), ex.getMessage());
		return new ResponseEntity<>(res, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(UnavailablemediaException.class)
	@ResponseStatus(HttpStatus.MULTIPLE_CHOICES)
	public ResponseEntity<ErrorResponse> processValidationError(UnavailablemediaException ex){
		ErrorResponse res = new ErrorResponse(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), ex.getMessage());
		return new ResponseEntity<>(res, HttpStatus.METHOD_NOT_ALLOWED);
	}
}
