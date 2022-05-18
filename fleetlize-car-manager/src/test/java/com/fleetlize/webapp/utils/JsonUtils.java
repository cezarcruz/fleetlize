package com.fleetlize.webapp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {

  public static String from(final Object object) {
    try {
      return new ObjectMapper().writeValueAsString(object);
    } catch (final JsonProcessingException e) {

      log.error("error creating json", e);
      return "null";
    }
  }

}
