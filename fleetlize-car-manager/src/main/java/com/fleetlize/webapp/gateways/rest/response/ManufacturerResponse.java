package com.fleetlize.webapp.gateways.rest.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ManufacturerResponse {

  private Long id;
  private String name;

}
