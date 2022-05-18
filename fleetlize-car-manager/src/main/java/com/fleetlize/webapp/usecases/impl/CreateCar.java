package com.fleetlize.webapp.usecases.impl;

import com.fleetlize.webapp.entities.Car;
import com.fleetlize.webapp.gateways.database.repositories.CarModelRepository;
import com.fleetlize.webapp.gateways.database.repositories.CarRepository;
import com.fleetlize.webapp.gateways.jms.CarCreationNotifier;
import com.fleetlize.webapp.usecases.BasicCreate;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCar implements BasicCreate<Car> {

  private final CarRepository carRepository;
  private final CarModelRepository carModelRepository;
  private final CarCreationNotifier carCreationNotifier;

  @Transactional
  public Car execute(final Car car) {
    log.info("executing create car");

    final var carModel = carModelRepository.findById(car.getCarModel().getId());

    return carModel.map(c -> {
      final var carInserted = carRepository.insert(car);
      carCreationNotifier.notify(carInserted);
      return carInserted.toBuilder().carModel(c).build();
    }).orElseThrow(RuntimeException::new);


  }

}
