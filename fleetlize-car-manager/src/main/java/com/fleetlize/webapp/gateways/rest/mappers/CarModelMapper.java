package com.fleetlize.webapp.gateways.rest.mappers;

import com.fleetlize.webapp.entities.CarModel;
import com.fleetlize.webapp.gateways.rest.request.CarModelRequest;
import com.fleetlize.webapp.gateways.rest.response.CarModelResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
    componentModel = "spring",
    uses = {
        ManufacturerMapper.class,
        CategoryMapper.class
    }
)
public interface CarModelMapper {

  @Mapping(source = "manufacturerId", target = "manufacturer.id")
  @Mapping(source = "categoryId", target = "category.id")
  CarModel from(final CarModelRequest carModelRequest);

  CarModelResponse from(final CarModel carModel);

  @Mapping(source = "carModel.manufacturerId", target = "manufacturer.id")
  @Mapping(source = "carModel.categoryId", target = "category.id")
  @Mapping(source = "id", target = "id")
  CarModel from(final CarModelRequest carModel, final Long id);

  @IterableMapping(qualifiedByName = "from")
  List<CarModelResponse> from(final List<CarModel> carModelList);

}
