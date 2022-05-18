package com.fleetlize.webapp.entities;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class Manufacturer {

  private Long id;
  private String name;
  private Date creationDate;
  private Date updateDate;

}
