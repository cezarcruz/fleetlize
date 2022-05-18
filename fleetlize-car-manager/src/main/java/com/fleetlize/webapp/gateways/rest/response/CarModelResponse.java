package com.fleetlize.webapp.gateways.rest.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CarModelResponse {

  private Long id;
  private String model;
  private ManufacturerResponse manufacturer;
  private CategoryResponse category;

}
