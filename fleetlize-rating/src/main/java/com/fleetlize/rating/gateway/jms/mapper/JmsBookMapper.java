package com.fleetlize.rating.gateway.jms.mapper;

import com.fleetlize.rating.entities.Booking;
import com.fleetlize.rating.gateway.jms.incoming.BookCreation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JmsBookMapper {
  Booking from(final BookCreation bookCreationObject);
}
