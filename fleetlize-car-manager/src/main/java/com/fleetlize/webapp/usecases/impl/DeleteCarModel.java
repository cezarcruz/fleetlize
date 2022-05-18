package com.fleetlize.webapp.usecases.impl;

import com.fleetlize.webapp.gateways.database.repositories.CarModelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DeleteCarModel {

  private CarModelRepository carModelRepository;

  public void execute(final Long id) {
    carModelRepository.delete(id);
  }

}
