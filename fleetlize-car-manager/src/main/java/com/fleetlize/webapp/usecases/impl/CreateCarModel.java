package com.fleetlize.webapp.usecases.impl;

import com.fleetlize.webapp.entities.CarModel;
import com.fleetlize.webapp.entities.Manufacturer;
import com.fleetlize.webapp.exceptions.ManufacturerNotFoundException;
import com.fleetlize.webapp.gateways.database.repositories.CarModelRepository;
import com.fleetlize.webapp.gateways.database.repositories.ManufacturerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CreateCarModel {

  private CarModelRepository carModelRepository;
  private ManufacturerRepository manufacturerRepository;

  public CarModel execute(final CarModel carModel) {

    log.info("executing create car model");

    Assert.notNull(carModel, "car model can't be null");

    final Optional<Manufacturer> manufacturer = manufacturerRepository.findById(carModel.getManufacturer().getId());

    return manufacturer.map( m -> {
      final var carModelSaved = carModelRepository.insert(carModel);
      return carModelSaved.toBuilder().manufacturer(m).build();
    }).orElseThrow(ManufacturerNotFoundException::new);

  }

}
