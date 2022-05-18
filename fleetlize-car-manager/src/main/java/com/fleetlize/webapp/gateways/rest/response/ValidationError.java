package com.fleetlize.webapp.gateways.rest.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ValidationError {

  private String field;
  private String message;

}
