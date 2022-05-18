package com.fleetlize.rating.gateway.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateRequest {
  private Long bookingId;
  private Integer score;
}
