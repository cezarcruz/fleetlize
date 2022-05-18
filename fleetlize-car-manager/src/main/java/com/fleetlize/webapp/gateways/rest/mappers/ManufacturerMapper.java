package com.fleetlize.webapp.gateways.rest.mappers;

import com.fleetlize.webapp.entities.Manufacturer;
import com.fleetlize.webapp.gateways.rest.request.ManufacturerRequest;
import com.fleetlize.webapp.gateways.rest.response.ManufacturerResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManufacturerMapper {

  Manufacturer from(final ManufacturerRequest request);

  ManufacturerResponse from(final Manufacturer manufacturer);

  @IterableMapping(qualifiedByName = "from")
  List<ManufacturerResponse> from(final List<Manufacturer> carModelList);
}
