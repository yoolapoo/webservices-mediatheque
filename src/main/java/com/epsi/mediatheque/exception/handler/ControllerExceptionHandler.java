package com.epsi.mediatheque.exception.handler;

import com.epsi.mediatheque.data.MediathequeApiStatus;
import com.epsi.mediatheque.dto.ErrorResponse;
import com.epsi.mediatheque.exception.DafReportPortalApiException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.GeneralSecurityException;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorResponse> throwableHandler(Throwable exception) {
		log.error(exception.getMessage(), exception);
		ErrorResponse res = new ErrorResponse(MediathequeApiStatus.DAF_REPORT_API_STATUS_1500.toString(), MediathequeApiStatus.DAF_REPORT_API_STATUS_1500.getReason());
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DafReportPortalApiException.class)
	public ResponseEntity<ErrorResponse> dafReportPortalApiExceptionHandler(DafReportPortalApiException exception) {
		ErrorResponse res = new ErrorResponse(exception.getCode(), exception.getMessage());
		return new ResponseEntity<>(res, exception.getStatus());
	}


	@ExceptionHandler(GeneralSecurityException.class)
	public ResponseEntity<ErrorResponse> generalSecurityExceptionHandler(GeneralSecurityException exception) {
		log.error(exception.getMessage(), exception);
		ErrorResponse res = new ErrorResponse(MediathequeApiStatus.DAF_REPORT_API_STATUS_1401.toString(), MediathequeApiStatus.DAF_REPORT_API_STATUS_1401.getReason());
		return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<ErrorResponse> malformedJwtExceptionHandler(MalformedJwtException exception) {
		log.error(exception.getMessage(), exception);
		ErrorResponse res = new ErrorResponse(MediathequeApiStatus.DAF_REPORT_API_STATUS_1402.toString(), MediathequeApiStatus.DAF_REPORT_API_STATUS_1402.getReason());
		return new ResponseEntity<>(res, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ResponseEntity<ErrorResponse> processValidationError(HttpRequestMethodNotSupportedException ex) {
		ErrorResponse res = new ErrorResponse(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), ex.getMessage());
		return new ResponseEntity<>(res, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> processValidationError() {
		ErrorResponse res = new ErrorResponse(MediathequeApiStatus.DAF_REPORT_API_STATUS_1400.toString(), MediathequeApiStatus.DAF_REPORT_API_STATUS_1400.getReason());
		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
	}
}
