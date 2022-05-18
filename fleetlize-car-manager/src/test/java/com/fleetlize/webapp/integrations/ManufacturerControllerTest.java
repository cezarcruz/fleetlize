package com.fleetlize.webapp.integrations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fleetlize.webapp.gateways.rest.request.ManufacturerRequest;
import com.fleetlize.webapp.utils.JsonUtils;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class ManufacturerControllerTest extends IntegrationTest {

  private static String URL = "/manufacturer";

  @Test
  public void shouldCreateNewManufacturer() throws Exception {

    final ManufacturerRequest manufacturerRequest =
        ManufacturerRequest.builder()
            .name("FIAT")
            .build();

    mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(JsonUtils.from(manufacturerRequest)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.name").value("FIAT"))
        .andDo(result -> {
          final Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

          mockMvc.perform(get(URL + "/" + id))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id").value(id))
              .andExpect(jsonPath("$.name").value(manufacturerRequest.getName()));
        })
    ;
  }

}
