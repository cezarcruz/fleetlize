package com.fleetlize.webapp.usecases.impl;

import com.fleetlize.webapp.entities.Manufacturer;
import com.fleetlize.webapp.gateways.database.repositories.ManufacturerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
public class CreateManufacturer {

  private ManufacturerRepository manufacturerRepository;

  public CreateManufacturer(final ManufacturerRepository manufacturerRepository) {
    this.manufacturerRepository = manufacturerRepository;
  }

  public Manufacturer execute(final Manufacturer manufacturer) {
    Assert.notNull(manufacturer, "manufacturer can't be null");
    log.info("creating new manufacturer");
    return manufacturerRepository.create(manufacturer);
  }

}
