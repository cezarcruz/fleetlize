package com.fleetlize.webapp.gateways.rest.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder(toBuilder = true)
public class CarModelRequest {

  @NotEmpty(message = "model can't be null")
  private String model;

  @NotNull(message = "model year can't be null")
  @Min(value = 1000, message = "model year must have 4 digits")
  @Max(value = 9999, message = "model year must have 4 digits")
  private Integer modelYear;

  @NotNull(message = "manufacturer can't be null")
  private Long manufacturerId;

  @NotNull(message = "category can't be null")
  private Long categoryId;

}
