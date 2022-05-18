package com.fleetlize.webapp.gateways.database.repositories.converters;

import com.fleetlize.webapp.entities.Car;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarConverter implements RowMapper<Car> {

  private final CarModelConverter carModelConverter;

  @Override
  public Car mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    return Car.builder()
        .id(resultSet.getLong("CAR_ID"))
        .plate(resultSet.getString("PLATE"))
        .mileage(resultSet.getInt("MILEAGE"))
        .carModel(carModelConverter.mapRow(resultSet, rowNum))
        .build();
  }

}
