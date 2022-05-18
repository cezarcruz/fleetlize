package com.fleetlize.webapp.gateways.rest.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

  private String message;
  private String code;

}
