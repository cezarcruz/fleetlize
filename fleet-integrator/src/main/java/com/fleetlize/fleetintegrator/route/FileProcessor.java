package com.fleetlize.fleetintegrator.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileProcessor extends RouteBuilder {

  @Override
  public void configure() throws Exception {

//    onException(Exception.class)
//        .handled(true)
//        .useOriginalBody()
//        .log("Erro maroto aqui: ${body}");

    errorHandler(
        deadLetterChannel("direct:error")
        .maximumRedeliveries(1)
        .redeliveryDelay(1000)
        .useOriginalMessage()
    );

    from("direct:error")
        .routeId("errorHandler")
          .log(LoggingLevel.ERROR, "Erro maroto: ${body}")
        .end();

    from("stream:file?fileName=/home/cezar/dev/files/arquivo.txt&scanStream=true&scanStreamDelay=1000")
        .routeId("fileReaderRoute")
        .multicast()
          .parallelProcessing()
          .threads(10)
          .to("direct:log", "direct:log2", "direct:log3")
        .end();

    from("direct:log")
        .routeId("loggerRoute")
          .log("${body}")
          .setProperty("nome", jsonpath("$.nome"))
          .setProperty("idade", jsonpath("$.idade"))
          .log("------------------------------")
          .log("${exchangeProperty[nome]}")
          .log("${exchangeProperty[idade]}");

    from("direct:log2")
        .routeId("loggerRoute2")
          .log("${body}")
          .setProperty("nome", jsonpath("$.nome"))
          .setProperty("idade", jsonpath("$.idade"))
          .log("------------------------------")
          .log("${exchangeProperty[nome]}")
          .log("${exchangeProperty[idade]}");

  }
}
