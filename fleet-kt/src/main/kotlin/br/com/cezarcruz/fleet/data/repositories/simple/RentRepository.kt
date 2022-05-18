package br.com.cezarcruz.fleet.data.repositories.simple

import br.com.cezarcruz.fleet.data.entities.RentEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface RentRepository : JpaRepository<RentEntity, Long>, JpaSpecificationExecutor<RentEntity> {
    fun findAllByCarId(carId: Long, pageable: Pageable): Page<RentEntity>
}