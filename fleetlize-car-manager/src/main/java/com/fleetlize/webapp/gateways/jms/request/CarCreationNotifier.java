package com.fleetlize.webapp.gateways.jms.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class CarCreationNotifier {
  private String plate;
}
