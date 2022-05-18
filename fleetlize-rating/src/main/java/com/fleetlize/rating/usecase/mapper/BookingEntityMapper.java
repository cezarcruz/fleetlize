package com.fleetlize.rating.usecase.mapper;

import com.fleetlize.rating.entities.Booking;
import com.fleetlize.rating.gateway.data.entities.BookingEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingEntityMapper {

  @Mapping(source = "carPlate", target = "car.plate")
  BookingEntity from(final Booking booking);

  @InheritInverseConfiguration
  Booking from(final BookingEntity bookingEntity);

}
