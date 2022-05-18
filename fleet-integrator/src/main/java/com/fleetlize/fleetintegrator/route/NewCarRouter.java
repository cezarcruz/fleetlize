package com.fleetlize.fleetintegrator.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class NewCarRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {

    errorHandler(
        deadLetterChannel("direct:error")
            .maximumRedeliveries(1)
            .redeliveryDelay(1000)
            .useOriginalMessage()
    );

    from("jms:queue:car-creation")
        .setProperty("plate", jsonpath("$.plate"))
        .log("${header.plate}")
        .end();

    from("direct:log3")
        .routeId("loggerRoute3")
        .log("${body}")
        .setProperty("nome", jsonpath("$.nome"))
        .setProperty("idade", jsonpath("$.idade"))
        .log("------------------------------")
        .log("${exchangeProperty[nome]}")
        .log("${exchangeProperty[idade]}");

  }
}
