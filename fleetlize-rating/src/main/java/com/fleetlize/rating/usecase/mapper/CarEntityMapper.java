package com.fleetlize.rating.usecase.mapper;


import com.fleetlize.rating.entities.Car;
import com.fleetlize.rating.gateway.data.entities.CarEntity;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CarEntityMapper {

    @Named("FromEntityToTable")
    CarEntity from(final Car car);

    @Named("FromTableToEntity")
    @InheritInverseConfiguration
    Car from(CarEntity save);

    @IterableMapping(qualifiedByName = "FromTableToEntity")
    List<Car> from(final List<CarEntity> cars);
}
