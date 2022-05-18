package com.fleetlize.webapp.gateways.database.repositories.converters;

import com.fleetlize.webapp.entities.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements RowMapper<Category> {

  @Override
  public Category mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    return Category.builder()
        .id(resultSet.getLong("CATEGORY_ID"))
        .name(resultSet.getString("CATEGORY_NAME"))
        .dailyPrice(resultSet.getBigDecimal("DAILY_PRICE"))
        .build();
  }
}
