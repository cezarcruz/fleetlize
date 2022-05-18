package com.fleetlize.webapp.exceptions;

public class ManufacturerNotFoundException extends RuntimeException {
  public ManufacturerNotFoundException() {
    super("Manufacturer Not Found");
  }
}
