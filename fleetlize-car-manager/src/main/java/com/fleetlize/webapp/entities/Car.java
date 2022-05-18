package com.fleetlize.webapp.entities;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class Car {
  private Long id;
  private String plate;
  private Integer mileage;
  private CarModel carModel;
  private Date creationDate;
  private Date updateDate;
}
