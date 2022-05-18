package com.fleetlize.webapp.integrations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fleetlize.webapp.gateways.rest.request.CarRequest;
import com.fleetlize.webapp.utils.JsonUtils;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class CarControllerTest extends IntegrationTest {

  private static final String URL = "/car";

  @Test
  public void shouldCreateCar() throws Exception {

    final CarRequest carRequest =
        CarRequest.builder()
            .mileage(0)
            .carModel(1L)
            .plate("CVY1234")
            .build();

    mockMvc.perform(post(URL)
        .content(JsonUtils.from(carRequest))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.plate").value(carRequest.getPlate()))
        .andExpect(jsonPath("$.carModel").isNotEmpty())
        .andExpect(jsonPath("$.carModel.model").value("KA"))

        .andDo(result -> {
          final Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

          mockMvc.perform(get(URL + "/" + id))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id").isNumber())
              .andExpect(jsonPath("$.plate").value(carRequest.getPlate()))
              .andExpect(jsonPath("$.carModel").isNotEmpty())
              .andExpect(jsonPath("$.carModel.model").value("KA"));

        });


  }


}
