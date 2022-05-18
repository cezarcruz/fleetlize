package com.fleetlize.webapp.gateways.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetlize.webapp.configurations.JmsParams;
import com.fleetlize.webapp.entities.Car;
import com.fleetlize.webapp.gateways.jms.mapper.CarCreationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CarCreationNotifier {

  private final JmsTemplate jmsTemplateTopic;
  private final JmsParams jmsParams;
  private final CarCreationMapper carCreationMapper;
  private final ObjectMapper objectMapper;

  @Value("${fleetlize.jms.enabled}")
  private boolean notificationEnabled;

  public CarCreationNotifier(final JmsTemplate jmsTemplateTopic,
      final JmsParams jmsParams,
      final CarCreationMapper carCreationMapper,
      final ObjectMapper objectMapper) {
    this.jmsTemplateTopic = jmsTemplateTopic;
    this.jmsParams = jmsParams;
    this.carCreationMapper = carCreationMapper;
    this.objectMapper = objectMapper;
  }

  public void notify(final Car car) {

    if (!notificationEnabled) {
      return;
    }

    log.info("notify car creation stream");
    log.debug("notify car creation in topic {}", jmsParams.getCarCreation());

    final var from = carCreationMapper.from(car);

    log.debug("notify car creation message {}", from);

    try {
      jmsTemplateTopic.convertAndSend(jmsParams.getCarCreation(), objectMapper.writeValueAsString(from));
    } catch (final JsonProcessingException ex) {
      log.error("Error in object to string", ex);
    }
  }

}
