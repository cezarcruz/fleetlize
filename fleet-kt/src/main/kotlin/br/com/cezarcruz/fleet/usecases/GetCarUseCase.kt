package br.com.cezarcruz.fleet.usecases

import br.com.cezarcruz.fleet.data.repositories.simple.CarRepository
import br.com.cezarcruz.fleet.mappers.CarMappers
import br.com.cezarcruz.fleet.web.response.CarResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GetCarUseCase(
    private val carRepository: CarRepository,
) {

    fun execute(pageable: Pageable): Page<CarResponse> {
        return carRepository.findAll(pageable).map {
            CarMappers.from(it)
        }
    }

    fun execute(plate: String): CarResponse? {
        return carRepository.findByPlate(plate)?.let {
            CarMappers.from(it)
        }
    }

    fun byCategory(categoryId: Long): List<CarResponse> {
        return carRepository.findAllByCategoryId(categoryId).map {
            CarMappers.from(it)
        }
    }

}