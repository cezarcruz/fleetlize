package com.fleetlize.rating.gateway.jms.incoming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookCreation {
  private Long id;
  private String carPlate;
}
