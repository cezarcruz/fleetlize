package com.fleetlize.webapp.usecases.impl;

import com.fleetlize.webapp.entities.CarModel;
import com.fleetlize.webapp.gateways.database.repositories.CarModelRepository;
import com.fleetlize.webapp.gateways.database.repositories.ManufacturerRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateCarModel {

  private final CarModelRepository carModelRepository;
  private final ManufacturerRepository manufacturerRepository;

  public Optional<CarModel> execute(final CarModel carModelToUpdate) {
    final Optional<CarModel> carModel =
        carModelRepository.findById(carModelToUpdate.getId());

    return carModel
        .flatMap(cm -> manufacturerRepository.findById(cm.getManufacturer().getId()))
        .map(manufacturer -> {
          carModelRepository.update(carModelToUpdate);
          return carModelToUpdate;
        });

  }

}
