package br.com.cezarcruz.fleet.data.repositories.complex

import br.com.cezarcruz.fleet.data.entities.CarEntity
import br.com.cezarcruz.fleet.data.entities.RentEntity
import br.com.cezarcruz.fleet.data.repositories.simple.RentRepository
import br.com.cezarcruz.fleet.web.request.filters.RentFilter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.persistence.criteria.Join
import javax.persistence.criteria.Predicate


@Service
class RentComplexRepository(
    private val rentRepository: RentRepository
) {

    fun findRentBy(filter: RentFilter, pageable: Pageable): Page<RentEntity> {
        val specification: Specification<RentEntity> = Specification { root, _, criteriaBuilder ->

            val predicates = mutableListOf<Predicate>()

            if (filter.car != null) {
                val carJoin: Join<RentEntity, CarEntity> = root.join("car")
                predicates.add(criteriaBuilder.equal(carJoin.get<Long>("id"), filter.car))
            }

            if (filter.id != null) {
                predicates.add(criteriaBuilder.equal(root.get<Long>("id"), filter.id))
            }

            if (filter.startDate != null) {
                predicates.add(criteriaBuilder.equal(root.get<LocalDate>("startDate"), filter.startDate))
            }

            if (filter.endDate != null) {
                predicates.add(criteriaBuilder.equal(root.get<LocalDate>("endDate"), filter.endDate))
            }

            criteriaBuilder.and(*predicates.toTypedArray())
        }

        return rentRepository.findAll(specification, pageable)
    }

}