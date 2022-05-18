package com.fleetlize.rating.gateway.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetlize.rating.gateway.jms.incoming.BookCreation;
import com.fleetlize.rating.gateway.jms.mapper.JmsBookMapper;
import com.fleetlize.rating.usecase.CreateBook;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BookCreationListener {

  private ObjectMapper objectMapper;
  private CreateBook createBook;
  private JmsBookMapper jmsBookMapper;

  @JmsListener(destination = "${fleetlize.jms.topic.book-creation}")
  public void messageReceived(final String bookCreation) {
    log.info("book creation notified {}", bookCreation);

    try {
      final var bookCreationObject = objectMapper.readValue(bookCreation, BookCreation.class);
      createBook.execute(jmsBookMapper.from(bookCreationObject));
    } catch (JsonProcessingException e) {
      log.error("error parsing notification", e);
    }
  }

}
