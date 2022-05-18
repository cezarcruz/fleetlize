package com.fleetlize.webapp.usecases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.fleetlize.webapp.entities.Category;
import com.fleetlize.webapp.gateways.database.repositories.CategoryRepository;
import com.fleetlize.webapp.usecases.impl.GetCategory;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetCategoryTest {

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private GetCategory getCategory;

  @Test
  public void shouldFindSomeCategoryById() {
    final Category category = Category.builder()
        .name("BASIC")
        .id(1L)
        .dailyPrice(BigDecimal.TEN)
        .build();

    when(categoryRepository.findBy(1L))
        .thenReturn(category);

    final Category cat = getCategory.execute(1L);

    assertThat(cat, notNullValue());
    assertThat(cat.getId(), is(1L));

    verify(categoryRepository, times(1)).findBy(1L);
    verifyNoMoreInteractions(categoryRepository);
  }

}
