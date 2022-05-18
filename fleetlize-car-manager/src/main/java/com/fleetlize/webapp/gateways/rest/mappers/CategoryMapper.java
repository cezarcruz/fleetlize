package com.fleetlize.webapp.gateways.rest.mappers;

import com.fleetlize.webapp.entities.Category;
import com.fleetlize.webapp.gateways.rest.request.CategoryRequest;
import com.fleetlize.webapp.gateways.rest.response.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  Category from(final CategoryRequest categoryRequest);
  CategoryResponse from(final Category category);
}
