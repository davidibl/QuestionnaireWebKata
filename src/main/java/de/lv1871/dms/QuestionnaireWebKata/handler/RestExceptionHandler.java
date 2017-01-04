package de.lv1871.dms.QuestionnaireWebKata.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import de.lv1871.dms.QuestionnaireWebKata.domain.RestError;
import de.lv1871.dms.QuestionnaireWebKata.exception.BadRequestException;
import de.lv1871.dms.QuestionnaireWebKata.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ResourceNotFoundException.class })
	protected ResponseEntity<Object> handleResourceNotFoundException(RuntimeException exception, WebRequest request) {

		return handleExceptionInternal(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ BadRequestException.class })
	protected ResponseEntity<Object> handleBadRequestException(BadRequestException exception, WebRequest request) {

		return handleExceptionInternal(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class })
	protected ResponseEntity<Object> handleGeneralException(RuntimeException exception, WebRequest request) {

		return handleExceptionInternal(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<Object> handleExceptionInternal(String message, HttpStatus status) {
		return handleExceptionInternal(new RestError(status.value(), message), status);
	}

	private ResponseEntity<Object> handleExceptionInternal(Object body, HttpStatus status) {
		return new ResponseEntity<Object>(body, status);
	}

}
