package com.fleetlize.webapp.entities;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Category {
  private Long id;
  private String name;
  private BigDecimal dailyPrice;
}
