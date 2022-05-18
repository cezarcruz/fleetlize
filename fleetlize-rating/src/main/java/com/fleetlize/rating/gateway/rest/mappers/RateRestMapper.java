package com.fleetlize.rating.gateway.rest.mappers;

import com.fleetlize.rating.entities.Rate;
import com.fleetlize.rating.gateway.rest.request.RateRequest;
import com.fleetlize.rating.gateway.rest.responses.RateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RateRestMapper {

  @Mapping(source = "bookingId", target = "booking.id")
  Rate from(final RateRequest rateRequest);

  @Mapping(source = "booking.id", target = "bookingId")
  RateResponse from(final Rate rate);

}
