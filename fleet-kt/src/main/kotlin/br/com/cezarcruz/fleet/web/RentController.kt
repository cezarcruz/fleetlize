package br.com.cezarcruz.fleet.web

import br.com.cezarcruz.fleet.usecases.CreateRentUseCase
import br.com.cezarcruz.fleet.usecases.GetRentUseCase
import br.com.cezarcruz.fleet.web.request.CreateRentRequest
import br.com.cezarcruz.fleet.web.request.PageableRequest
import br.com.cezarcruz.fleet.web.request.filters.RentFilter
import br.com.cezarcruz.fleet.web.request.toPageable
import br.com.cezarcruz.fleet.web.response.PageWrapperResponse
import br.com.cezarcruz.fleet.web.response.RentResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/rent")
class RentController(
    private val createRentUseCase: CreateRentUseCase,
    private val getRentUseCase: GetRentUseCase
) {

    @PostMapping
    fun createRent(@Valid @RequestBody createRentRequest: CreateRentRequest): ResponseEntity<RentResponse> {
        val rentResponse = createRentUseCase.execute(createRentRequest)

        return ResponseEntity.status(HttpStatus.CREATED).body(rentResponse)
    }

    @GetMapping
    fun getBy(
        rentFilter: RentFilter,
        pageRequest: PageableRequest
    ): ResponseEntity<PageWrapperResponse<RentResponse>> {
        val rents = getRentUseCase.getBy(rentFilter, pageRequest.toPageable())

        return ResponseEntity.ok(PageWrapperResponse(rents))
    }

    @GetMapping("/car/{carId}")
    fun getByCar(
        @PathVariable carId: Long,
        pageRequest: PageableRequest
    ): ResponseEntity<PageWrapperResponse<RentResponse>> {
        val rents = getRentUseCase.getBy(carId, pageRequest.toPageable())
        return ResponseEntity.ok(PageWrapperResponse(rents))
    }
}