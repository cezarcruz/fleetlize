package com.fleetlize.webapp.gateways.database.repositories;

import com.fleetlize.webapp.entities.Manufacturer;
import com.fleetlize.webapp.gateways.database.repositories.converters.ManufacturerConverter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ManufacturerRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final ManufacturerConverter manufacturerConverter;

  public Manufacturer create(final Manufacturer manufacturer) {

    log.debug("inserting new manufacturer = {}", manufacturer);

    final KeyHolder keyHolder = new GeneratedKeyHolder();
    final Timestamp creationDate = new Timestamp(new Date().getTime());
    final MapSqlParameterSource params = new MapSqlParameterSource();

    params.addValue("NAME", manufacturer.getName());
    params.addValue("CREATION_DATE", creationDate);

    final String sql = "INSERT INTO MANUFACTURER (NAME, CREATION_DATE) VALUES (:NAME, :CREATION_DATE)";

    jdbcTemplate.update(sql, params, keyHolder);

    final Long id = keyHolder.getKey() != null ? keyHolder.getKey().longValue() : null;

    return manufacturer.toBuilder().creationDate(creationDate).id(id).build();

  }

  public List<Manufacturer> list() {

    log.debug("getting all manufacturers");

    final String sql = "SELECT MANUFACTURER_ID, NAME, CREATION_DATE, UPDATE_DATE FROM MANUFACTURER";
    return jdbcTemplate.query(sql, manufacturerConverter::mapRow);
  }

  public Optional<Manufacturer> findById(final Long id) {

    log.debug("getting manufacturer by id = {}", id);

    final String sql = "SELECT MANUFACTURER_ID, NAME, CREATION_DATE, UPDATE_DATE FROM MANUFACTURER WHERE MANUFACTURER_ID = :ID";

    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("ID", id);

    try {
      return Optional.ofNullable(jdbcTemplate.queryForObject(sql, params, manufacturerConverter::mapRow));
    } catch (final EmptyResultDataAccessException ex) {
      log.error("error getting manufacturer");
      return Optional.empty();
    }
  }
}
