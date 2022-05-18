package com.fleetlize.webapp.gateways.rest;

import com.fleetlize.webapp.exceptions.ManufacturerNotFoundException;
import com.fleetlize.webapp.gateways.rest.response.ErrorResponse;
import com.fleetlize.webapp.gateways.rest.response.FieldErrorResponse;
import com.fleetlize.webapp.gateways.rest.response.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<FieldErrorResponse> validationExceptionHandler(final MethodArgumentNotValidException ex) {

    log.error("validation error", ex);

    final BindingResult result = ex.getBindingResult();

    final List<ValidationError> errors
        = result.getFieldErrors()
        .stream()
        .map(this::getValidationError)
        .collect(Collectors.toList());

    final FieldErrorResponse validationError = FieldErrorResponse.builder().errors(errors).message("validation error").build();

    return ResponseEntity.badRequest().body(validationError);

  }

  private ValidationError getValidationError(final FieldError fieldError) {
    return ValidationError.builder().field(fieldError.getField()).message(fieldError.getDefaultMessage()).build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> all(final Exception ex) {

    log.error("getting a generic exception", ex);

    final ErrorResponse errorResponse = ErrorResponse.builder().message("Internal Server Error").build();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

  }

  @ExceptionHandler(ManufacturerNotFoundException.class)
  public ResponseEntity<ErrorResponse> manufacturerNotFoundException(final ManufacturerNotFoundException e) {
    final ErrorResponse errorResponse = ErrorResponse.builder().message(e.getMessage()).build();

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }
}
