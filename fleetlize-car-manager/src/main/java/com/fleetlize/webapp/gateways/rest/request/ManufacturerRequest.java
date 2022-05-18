package com.fleetlize.webapp.gateways.rest.request;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerRequest {

  @NotEmpty(message = "can't be null or empty")
  private String name;
}
