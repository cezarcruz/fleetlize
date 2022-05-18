package com.fleetlize.webapp.gateways.rest;

import com.fleetlize.webapp.entities.Car;
import com.fleetlize.webapp.gateways.rest.mappers.CarMapper;
import com.fleetlize.webapp.gateways.rest.request.CarRequest;
import com.fleetlize.webapp.gateways.rest.response.CarResponse;
import com.fleetlize.webapp.usecases.impl.CreateCar;
import com.fleetlize.webapp.usecases.impl.GetCar;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
@Api(value = "Car")
public class CarController {

  private final CreateCar createCar;
  private final CarMapper carMapper;
  private final GetCar getCar;

  @ApiOperation(value = "Creates a new Car")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Car created successfully"),
      @ApiResponse(code = 400, message = "API doesn't recognize sent parameters"),
      @ApiResponse(code = 412, message = "Car already exists"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
  })
  @PostMapping
  public ResponseEntity<CarResponse> create(@RequestBody @Valid final CarRequest carRequest) {

    final var carToSave = carMapper.from(carRequest);
    final var carSaved = createCar.execute(carToSave);
    final var carResponse = carMapper.from(carSaved);
    return ResponseEntity.status(HttpStatus.CREATED).body(carResponse);

  }

  @ApiOperation(value = "Get Car by id")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Car"),
      @ApiResponse(code = 404, message = "Car not found"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
  })
  @GetMapping("/{id}")
  public ResponseEntity<CarResponse> findBy(@PathVariable final Long id) {
    final Optional<Car> car = getCar.execute(id);
    return car.map(c  -> ResponseEntity.ok(carMapper.from(c)))
        .orElse(ResponseEntity.notFound().build());
  }

  @ApiOperation(value = "Get Car list")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Car"),
      @ApiResponse(code = 500, message = "Internal Server error")
  })
  @GetMapping
  public ResponseEntity<List<CarResponse>> listAll() {
    final List<Car> carlist = getCar.execute();

    return ResponseEntity.ok(carMapper.from(carlist));

  }


}
