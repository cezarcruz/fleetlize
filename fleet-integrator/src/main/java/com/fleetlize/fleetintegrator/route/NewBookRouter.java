package com.fleetlize.fleetintegrator.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class NewBookRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("jms:queue:book-creation")
        .log("${body}")
        .end();
  }

}
