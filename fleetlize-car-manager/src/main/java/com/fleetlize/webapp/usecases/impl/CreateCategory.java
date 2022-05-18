package com.fleetlize.webapp.usecases.impl;

import com.fleetlize.webapp.entities.Category;
import com.fleetlize.webapp.gateways.database.repositories.CategoryRepository;
import com.fleetlize.webapp.usecases.BasicCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategory implements BasicCreate<Category> {

  private final CategoryRepository categoryRepository;

  @Override
  public Category execute(final Category category) {
    return categoryRepository.insert(category);
  }
}
