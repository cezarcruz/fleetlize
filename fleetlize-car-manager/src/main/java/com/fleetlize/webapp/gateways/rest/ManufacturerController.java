package com.fleetlize.webapp.gateways.rest;

import com.fleetlize.webapp.entities.Manufacturer;
import com.fleetlize.webapp.gateways.rest.mappers.ManufacturerMapper;
import com.fleetlize.webapp.gateways.rest.request.ManufacturerRequest;
import com.fleetlize.webapp.gateways.rest.response.ManufacturerResponse;
import com.fleetlize.webapp.usecases.impl.CreateManufacturer;
import com.fleetlize.webapp.usecases.impl.GetManufacturer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/manufacturer")
@AllArgsConstructor
@Api(value = "Manufacturers")
public class ManufacturerController {

  private ManufacturerMapper manufacturerMapper;
  private CreateManufacturer createManufacturer;
  private GetManufacturer getManufacturer;

  @ApiOperation(value = "Creates a new Manufacturer")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Manufacturer created successfully"),
      @ApiResponse(code = 400, message = "API doesn't recognize sent parameters"),
      @ApiResponse(code = 412, message = "Manufacturer already exists"),
      @ApiResponse(code = 500, message = "Internal Server ErrorResponse"),
  })
  @PostMapping
  public ResponseEntity<ManufacturerResponse> create(@Valid @RequestBody final ManufacturerRequest manufacturerRequest) {

    log.info("Creating a new manufacturer: {}", manufacturerRequest.getName());

    final Manufacturer manufacturer = manufacturerMapper.from(manufacturerRequest);

    final Manufacturer manufacturerCreated = createManufacturer.execute(manufacturer);

    final ManufacturerResponse response = manufacturerMapper.from(manufacturerCreated);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(response);

  }

  @ApiOperation(value = "List Manufacturer")
  @ApiResponses({
      @ApiResponse(code = 200, message = "List successfully"),
      @ApiResponse(code = 400, message = "API doesn't recognize sent parameters"),
      @ApiResponse(code = 500, message = "Internal Server ErrorResponse"),
  })
  @GetMapping("/")
  public ResponseEntity<List<ManufacturerResponse>> list() {

    log.info("Getting all manufacturers");

    final List<Manufacturer> manufacturerList = getManufacturer.execute();
    final List<ManufacturerResponse> manufacturerResponses
        = manufacturerMapper.from(manufacturerList);

    return ResponseEntity.ok(manufacturerResponses);
  }

  @ApiOperation(value = "Get Manufacturer by Id")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Manufacturer Detail"),
      @ApiResponse(code = 404, message = "Manufacturer not found")
  })
  @GetMapping("/{id}")
  public ResponseEntity<ManufacturerResponse> getById(@PathVariable final Long id) {

    log.info("getting manufacturer by id {}", id);

    final Manufacturer manufacturer = getManufacturer.execute(id);

    if (manufacturer == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    final ManufacturerResponse manufacturerResponse = manufacturerMapper.from(manufacturer);

    return ResponseEntity.ok(manufacturerResponse);

  }


}
