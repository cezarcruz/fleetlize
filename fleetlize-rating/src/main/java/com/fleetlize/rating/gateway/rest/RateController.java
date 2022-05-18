package com.fleetlize.rating.gateway.rest;

import com.fleetlize.rating.gateway.rest.mappers.RateRestMapper;
import com.fleetlize.rating.gateway.rest.request.RateRequest;
import com.fleetlize.rating.gateway.rest.responses.RateResponse;
import com.fleetlize.rating.usecase.RateCar;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/rate")
public class RateController {

  private RateCar rateCar;
  private RateRestMapper rateRestMapper;

  @PostMapping
  public ResponseEntity<RateResponse> rate(final RateRequest rateRequest) {

    final var rate = rateRestMapper.from(rateRequest);

    final var rated = rateCar.execute(rate);

    return ResponseEntity.status(HttpStatus.CREATED).body(rateRestMapper.from(rated));
  }
}
