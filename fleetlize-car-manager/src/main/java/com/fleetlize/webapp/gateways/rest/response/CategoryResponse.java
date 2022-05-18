package com.fleetlize.webapp.gateways.rest.response;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class CategoryResponse {
  private Long id;
  private String name;
  private BigDecimal dailyPrice;
}
