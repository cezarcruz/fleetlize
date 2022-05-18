package com.fleetlize.webapp.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "fleetlize.jms.topic")
public class JmsParams {
  private String carCreation;
}
