package com.fleetlize.rating.gateway.rest.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateResponse {
  private Long id;
  private Long bookingId;
  private Integer score;
}
