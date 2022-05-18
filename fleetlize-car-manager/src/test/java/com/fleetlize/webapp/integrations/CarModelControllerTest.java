package com.fleetlize.webapp.integrations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fleetlize.webapp.gateways.rest.request.CarModelRequest;
import com.fleetlize.webapp.utils.JsonUtils;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class CarModelControllerTest extends IntegrationTest {

  private static final String URL = "/car-model";

  @Test
  public void shouldCrudNewModel() throws Exception {

    final CarModelRequest carModelRequest =
        CarModelRequest.builder()
            .categoryId(1L)
            .model("KA SEDAN")
            .modelYear(2020)
            .manufacturerId(1L)
            .build();

    mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtils.from(carModelRequest)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.model").value(carModelRequest.getModel()))
        .andExpect(jsonPath("$.manufacturer.name").value("Ford"))
        .andDo(result -> {
          final Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

          mockMvc.perform(get(URL + "/" + id))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id").value(id))
              .andExpect(jsonPath("$.model").value(carModelRequest.getModel()))
              .andExpect(jsonPath("$.manufacturer.name").value("Ford"));
        }).andDo(result -> {
          final Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

          final CarModelRequest modelToUpdate =
              carModelRequest.toBuilder().model("KA SEDAN PLUS").build();

          mockMvc.perform(put(URL + "/" + id)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(JsonUtils.from(modelToUpdate)))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.model").value(modelToUpdate.getModel()));

        }).andDo(result -> {
          final Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
          mockMvc.perform(delete(URL + "/" + id)
              .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isNoContent());

          mockMvc.perform(get(URL + "/" + id).contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isNotFound());
        })
    ;
  }

}
