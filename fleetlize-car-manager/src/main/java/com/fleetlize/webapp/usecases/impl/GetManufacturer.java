package com.fleetlize.webapp.usecases.impl;

import com.fleetlize.webapp.entities.Manufacturer;
import com.fleetlize.webapp.exceptions.ManufacturerNotFoundException;
import com.fleetlize.webapp.gateways.database.repositories.ManufacturerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class GetManufacturer {

  private ManufacturerRepository manufacturerRepository;

  public List<Manufacturer> execute() {

    log.info("executing get manufacturer");

    return manufacturerRepository.list();
  }


  public Manufacturer execute(final Long id) {

    Assert.notNull(id, "id can't be null");

    log.info("executing get manufacturer");

    final Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
    return manufacturer.orElseThrow(ManufacturerNotFoundException::new);
  }
}
