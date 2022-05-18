package com.fleetlize.rating.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Rate {
  private Long id;
  private Booking booking;
  private Integer score;
}
