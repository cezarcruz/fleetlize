package com.fleetlize.rating.gateway.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleetlize.rating.exceptions.CarAlreadyExists;
import com.fleetlize.rating.gateway.jms.incoming.CarCreation;
import com.fleetlize.rating.gateway.jms.mapper.JmsCarMapper;
import com.fleetlize.rating.usecase.CreateCar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CarCreationListener {

    private ObjectMapper objectMapper;
    private JmsCarMapper jmsCarMapper;
    private CreateCar createCar;

    public CarCreationListener(final ObjectMapper objectMapper,
                               final JmsCarMapper jmsCarMapper,
                               final CreateCar createCar) {
        this.objectMapper = objectMapper;
        this.jmsCarMapper = jmsCarMapper;
        this.createCar = createCar;
    }

    @JmsListener(destination = "${fleetlize.jms.topic.car-creation}")
    public void messageReceived(final String carCreation) {
        log.info("car creation notified {}", carCreation);

        try {
            final var carCreationObject = objectMapper.readValue(carCreation, CarCreation.class);
            final var carCreated = createCar.execute(jmsCarMapper.from(carCreationObject));
            log.debug("car created {}", carCreated);
        } catch (final JsonProcessingException e) {
            log.error("error parsing notification", e);
        } catch (final CarAlreadyExists e) {
            log.error("car already exists", e);
        }
    }

}
