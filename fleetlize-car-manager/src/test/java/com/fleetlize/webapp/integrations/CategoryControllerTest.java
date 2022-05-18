package com.fleetlize.webapp.integrations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fleetlize.webapp.gateways.rest.request.CategoryRequest;
import com.fleetlize.webapp.utils.JsonUtils;
import com.jayway.jsonpath.JsonPath;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class CategoryControllerTest extends IntegrationTest {

  public static String URL = "/category";

  @Test
  public void shouldCreateNewCategory() throws Exception {
    final CategoryRequest categoryRequest =
        CategoryRequest.builder()
            .dailyPrice(new BigDecimal("10.44"))
            .name("Premium")
            .build();

    mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtils.from(categoryRequest)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.name").value(categoryRequest.getName()))
        .andExpect(jsonPath("$.dailyPrice").value("10.44"))
        .andDo(result -> {
          final Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

          mockMvc.perform(get(URL + "/" + id))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id").value(id))
              .andExpect(jsonPath("$.name").value(categoryRequest.getName()))
              .andExpect(jsonPath("$.dailyPrice").value("10.44"));
        })
    ;
  }

}
