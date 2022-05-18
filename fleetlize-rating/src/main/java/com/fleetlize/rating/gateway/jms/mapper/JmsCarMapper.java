package com.fleetlize.rating.gateway.jms.mapper;

import com.fleetlize.rating.entities.Car;
import com.fleetlize.rating.gateway.jms.incoming.CarCreation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JmsCarMapper {

    Car from(final CarCreation carCreation);
}
