package com.fleetlize.webapp;

import com.fleetlize.webapp.configurations.JmsParams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ JmsParams.class })
public class WebAppApplication {

  public static void main(final String...args) {
    SpringApplication.run(WebAppApplication.class, args);
  }

}
