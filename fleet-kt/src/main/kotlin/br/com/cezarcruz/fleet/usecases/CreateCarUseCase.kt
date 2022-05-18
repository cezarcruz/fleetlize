package br.com.cezarcruz.fleet.usecases

import br.com.cezarcruz.fleet.data.repositories.simple.CarRepository
import br.com.cezarcruz.fleet.data.repositories.simple.CategoryRepository
import br.com.cezarcruz.fleet.mappers.CarMappers
import br.com.cezarcruz.fleet.web.request.CreateCarRequest
import br.com.cezarcruz.fleet.web.response.CarResponse
import org.springframework.stereotype.Service

@Service
class CreateCarUseCase(
    private val carRepository: CarRepository,
    private val categoryRepository: CategoryRepository
) {

    fun execute(createCarRequest: CreateCarRequest): CarResponse {

        val categoryEntity = categoryRepository.getOne(createCarRequest.category!!)

        val savedCar =
            carRepository.saveAndFlush(CarMappers.from(createCarRequest, categoryEntity))

        return CarMappers.from(savedCar)

    }

}
