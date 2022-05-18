package com.fleetlize.webapp.gateways.jms.mapper;

import com.fleetlize.webapp.entities.Car;
import com.fleetlize.webapp.gateways.jms.request.CarCreationNotifier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarCreationMapper {

  CarCreationNotifier from(final Car car);

}
