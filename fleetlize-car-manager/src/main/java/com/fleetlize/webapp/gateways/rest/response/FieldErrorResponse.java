package com.fleetlize.webapp.gateways.rest.response;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class FieldErrorResponse {
  private String message;
  private List<ValidationError> errors;
}
