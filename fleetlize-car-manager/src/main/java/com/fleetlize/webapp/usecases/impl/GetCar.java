package com.fleetlize.webapp.usecases.impl;

import com.fleetlize.webapp.entities.Car;
import com.fleetlize.webapp.gateways.database.repositories.CarRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCar {

  private final CarRepository carRepository;

  public Optional<Car> execute(final Long id) {
    return Optional.ofNullable(carRepository.findBy(id));
  }

  public List<Car> execute() {
    return carRepository.findAll();
  }
}
