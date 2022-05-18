package com.fleetlize.rating.gateway.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "booking")
@Data
@Builder
public class BookingEntity {

  @Id
  private Long id;

  @OneToOne
  private CarEntity car;
}
