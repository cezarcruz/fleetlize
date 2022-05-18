package br.com.cezarcruz.fleet.usecases

import br.com.cezarcruz.fleet.data.repositories.simple.CarRepository
import br.com.cezarcruz.fleet.data.repositories.simple.RentRepository
import br.com.cezarcruz.fleet.mappers.RentMappers
import br.com.cezarcruz.fleet.web.request.CreateRentRequest
import br.com.cezarcruz.fleet.web.response.RentResponse
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.temporal.ChronoUnit

@Service
class CreateRentUseCase(
    private val rentRepository: RentRepository,
    private val carRepository: CarRepository,
    private val checkCarAvailableUseCase: CheckCarAvailableUseCase
) {

    fun execute(createRentRequest: CreateRentRequest): RentResponse {

        checkNotNull(createRentRequest.car)

        if (!checkCarAvailableUseCase.isAvailable(
                createRentRequest.car,
                createRentRequest.startDate!!,
                createRentRequest.endDate!!
            )
        ) {
            throw RuntimeException("car is not available for ${createRentRequest.startDate}")
        }

        val carEntity = carRepository.getOne(createRentRequest.car)

        val daysInterval =
            createRentRequest.startDate.until(createRentRequest.endDate, ChronoUnit.DAYS)

        val totalPrice = carEntity.category.price.times(BigDecimal.valueOf(daysInterval))

        val rentEntity = RentMappers.from(createRentRequest, carEntity, totalPrice)

        val savedRent = rentRepository.saveAndFlush(rentEntity)

        return RentMappers.from(savedRent)

    }

}