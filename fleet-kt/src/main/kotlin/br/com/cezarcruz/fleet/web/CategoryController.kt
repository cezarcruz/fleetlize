package br.com.cezarcruz.fleet.web

import br.com.cezarcruz.fleet.usecases.CreateCategoryUseCase
import br.com.cezarcruz.fleet.usecases.GetCategoryUseCase
import br.com.cezarcruz.fleet.web.request.CreateCategoryRequest
import br.com.cezarcruz.fleet.web.request.PageableRequest
import br.com.cezarcruz.fleet.web.request.toPageable
import br.com.cezarcruz.fleet.web.response.CategoryResponse
import br.com.cezarcruz.fleet.web.response.PageWrapperResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/category")
class CategoryController(private val createCategoryUseCase: CreateCategoryUseCase, private val getCategoryUseCase: GetCategoryUseCase) {

    @PostMapping
    fun create(@Valid @RequestBody createCategoryRequest: CreateCategoryRequest): ResponseEntity<CategoryResponse> {
        val categoryDomain = createCategoryUseCase.execute(createCategoryRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDomain)
    }

    @GetMapping
    fun getAll(
        pageableRequest: PageableRequest
    ): PageWrapperResponse<CategoryResponse> {
        return PageWrapperResponse(getCategoryUseCase.execute(pageableRequest.toPageable()))
    }

}