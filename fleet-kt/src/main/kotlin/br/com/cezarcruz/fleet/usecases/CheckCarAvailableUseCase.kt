package br.com.cezarcruz.fleet.usecases

import br.com.cezarcruz.fleet.data.repositories.simple.CarRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CheckCarAvailableUseCase(
    private val carRepository: CarRepository
) {

    fun isAvailable(car: Long, startDate: LocalDate, endDate: LocalDate): Boolean {
        return carRepository.findAvailable(startDate, car) != null
    }

}