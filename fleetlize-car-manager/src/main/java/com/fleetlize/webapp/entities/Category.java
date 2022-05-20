package com.fleetlize.webapp.entities;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder(toBuilder = true)
public class Category {
  private Long id;
  private String name;
  private BigDecimal dailyPrice;
}
