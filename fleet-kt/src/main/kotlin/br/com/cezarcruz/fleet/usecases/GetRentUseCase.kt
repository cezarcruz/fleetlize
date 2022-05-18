package br.com.cezarcruz.fleet.usecases

import br.com.cezarcruz.fleet.data.repositories.complex.RentComplexRepository
import br.com.cezarcruz.fleet.data.repositories.simple.RentRepository
import br.com.cezarcruz.fleet.mappers.RentMappers
import br.com.cezarcruz.fleet.web.request.filters.RentFilter
import br.com.cezarcruz.fleet.web.response.RentResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GetRentUseCase(
    private val rentRepository: RentRepository,
    private val rentComplexRepository: RentComplexRepository
) {

    fun getBy(carId: Long, pageable: Pageable): Page<RentResponse> {
        return rentRepository.findAllByCarId(carId, pageable).map {
            RentMappers.from(it)
        }
    }

    fun getBy(rentFilter: RentFilter, pageable: Pageable): Page<RentResponse> {
        return rentComplexRepository.findRentBy(rentFilter, pageable).map {
            RentMappers.from(it)
        }
    }

}