package com.fleetlize.webapp.usecases.impl;

import com.fleetlize.webapp.entities.CarModel;
import com.fleetlize.webapp.gateways.database.repositories.CarModelRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetCarModel {

  private final CarModelRepository carModelRepository;

  public List<CarModel> execute() {

    log.info("executing get car model");

    return carModelRepository.list();
  }

  public Optional<CarModel> execute(final Long id) {

    Assert.notNull(id, "car model id can't be null");

    log.info("executing get car model by id = {}", id);

    return carModelRepository.findById(id);
  }

}
