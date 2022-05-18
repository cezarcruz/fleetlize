package com.fleetlize.webapp.gateways.database.repositories.converters;

import com.fleetlize.webapp.entities.Manufacturer;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerConverter implements RowMapper<Manufacturer> {

  @Override
  public Manufacturer mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    return Manufacturer.builder()
        .id(resultSet.getLong("MANUFACTURER_ID"))
        .name(resultSet.getString("NAME"))
        .creationDate(resultSet.getTimestamp("CREATION_DATE"))
        .updateDate(resultSet.getTimestamp("UPDATE_DATE"))
        .build();
  }
}
