package com.fleetlize.webapp.gateways.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CarResponse {
  private Long id;
  private String plate;
  private Integer mileage;
  private CarModelResponse carModel;
}
