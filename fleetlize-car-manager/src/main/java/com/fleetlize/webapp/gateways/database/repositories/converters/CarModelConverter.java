package com.fleetlize.webapp.gateways.database.repositories.converters;

import com.fleetlize.webapp.entities.CarModel;
import com.fleetlize.webapp.entities.Manufacturer;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarModelConverter implements RowMapper<CarModel> {

  private final CategoryConverter categoryConverter;

  @Override
  public CarModel mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {

    final Manufacturer manufacturer = Manufacturer.builder()
        .id(resultSet.getLong("MANUFACTURER_ID"))
        .name(resultSet.getString("NAME"))
        .build();

    return CarModel.builder()
        .id(resultSet.getLong("CAR_MODEL_ID"))
        .model(resultSet.getString("MODEL_NAME"))
        .modelYear(resultSet.getInt("MODEL_YEAR"))
        .manufacturer(manufacturer)
        .category(categoryConverter.mapRow(resultSet, rowNum))
        .build();

  }
}
