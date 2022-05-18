package com.fleetlize.webapp.gateways.database.repositories;

import com.fleetlize.webapp.entities.Category;
import com.fleetlize.webapp.gateways.database.Queries;
import com.fleetlize.webapp.gateways.database.repositories.converters.CategoryConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CategoryRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final CategoryConverter categoryConverter;

  public Category insert(final Category category) {

    final KeyHolder keyHolder = new GeneratedKeyHolder();
    final MapSqlParameterSource params = new MapSqlParameterSource();

    params.addValue("NAME", category.getName());
    params.addValue("DAILY_PRICE", category.getDailyPrice());

    jdbcTemplate.update(Queries.INSERT_CATEGORY, params, keyHolder);

    final Long id = keyHolder.getKey() != null ? keyHolder.getKey().longValue() : null;
    log.debug("category insert successfully with id {}", id);

    return category.toBuilder().id(id).build();

  }

  public Category findBy(final Long id) {
    log.debug("finding category by id = {}", id);
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("ID", id);

    return jdbcTemplate.queryForObject(Queries.FIND_CATEGORY_BY_ID, params, categoryConverter);

  }
}
