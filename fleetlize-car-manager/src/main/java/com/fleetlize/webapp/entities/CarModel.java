package com.fleetlize.webapp.entities;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class CarModel {

  private Long id;
  private Manufacturer manufacturer;
  private Category category;
  private String model;
  private Integer modelYear;
  private Date creationDate;
  private Date updateDate;

}
