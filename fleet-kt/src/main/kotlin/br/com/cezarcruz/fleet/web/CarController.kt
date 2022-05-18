package br.com.cezarcruz.fleet.web

import br.com.cezarcruz.fleet.usecases.CreateCarUseCase
import br.com.cezarcruz.fleet.usecases.GetCarUseCase
import br.com.cezarcruz.fleet.usecases.GetCategoryUseCase
import br.com.cezarcruz.fleet.web.request.CreateCarRequest
import br.com.cezarcruz.fleet.web.request.PageableRequest
import br.com.cezarcruz.fleet.web.request.toPageable
import br.com.cezarcruz.fleet.web.response.CarResponse
import br.com.cezarcruz.fleet.web.response.PageWrapperResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/car")
class CarController(
    private val getCarUseCase: GetCarUseCase,
    private val createCarUseCase: CreateCarUseCase,
    private val getCategoryUseCase: GetCategoryUseCase
) {

    @GetMapping
    fun findAll(
        pageableRequest: PageableRequest
    ): PageWrapperResponse<CarResponse> {
        return PageWrapperResponse(getCarUseCase.execute(pageableRequest.toPageable()))
    }

    @PostMapping
    fun create(@Validated @RequestBody createCarRequest: CreateCarRequest): ResponseEntity<CarResponse> {

        val categoryDomain = getCategoryUseCase.execute(createCarRequest.category!!)

        return categoryDomain?.let {
            val carDomain = createCarUseCase.execute(createCarRequest)
            ResponseEntity.status(HttpStatus.CREATED).body(carDomain)
        } ?: return ResponseEntity.notFound().build()

    }

    @GetMapping("/{plate}")
    fun findById(@PathParam("plate") plate: String): ResponseEntity<CarResponse> {
        return getCarUseCase.execute(plate)?.let {
            return ResponseEntity.ok(it)
        } ?: return ResponseEntity.notFound().build()
    }

    @GetMapping("/category/{categoryId}")
    fun findByCategory(@PathVariable("categoryId") categoryId: Long): ResponseEntity<List<CarResponse>> {
        getCarUseCase.byCategory(categoryId).run {
            return ResponseEntity.ok(this)
        }
    }

}