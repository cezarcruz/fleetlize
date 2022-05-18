package com.fleetlize.webapp.gateways.rest;

import com.fleetlize.webapp.entities.Category;
import com.fleetlize.webapp.gateways.rest.mappers.CategoryMapper;
import com.fleetlize.webapp.gateways.rest.request.CategoryRequest;
import com.fleetlize.webapp.gateways.rest.response.CategoryResponse;
import com.fleetlize.webapp.usecases.impl.CreateCategory;
import com.fleetlize.webapp.usecases.impl.GetCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/category")
@RequiredArgsConstructor
@Api(value = "Category")
public class CategoryController {

  private final CategoryMapper categoryMapper;
  private final CreateCategory createCategory;
  private final GetCategory getCategory;

  @ApiOperation(value = "Creates a new Category")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Category created successfully"),
      @ApiResponse(code = 400, message = "API doesn't recognize sent parameters"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
  })
  @PostMapping
  public ResponseEntity<CategoryResponse> create(@RequestBody @Valid final CategoryRequest categoryRequest) {
    final Category category = categoryMapper.from(categoryRequest);
    final Category categoryCreated = createCategory.execute(category);
    final CategoryResponse categoryResponse = categoryMapper.from(categoryCreated);
    return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
  }

  @ApiOperation(value = "Get Category by id")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Category created successfully"),
      @ApiResponse(code = 404, message = "Category not found"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
  })
  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponse> by(@PathVariable final Long id) {
    final Category category = getCategory.execute(id);
    final CategoryResponse categoryResponse = categoryMapper.from(category);
    return ResponseEntity.ok(categoryResponse);
  }

}
