package com.fleetlize.webapp.usecases.impl;

import com.fleetlize.webapp.entities.Category;
import com.fleetlize.webapp.gateways.database.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCategory {

  private final CategoryRepository categoryRepository;

  public Category execute(final Long id) {
    return categoryRepository.findBy(id);
  }
}
