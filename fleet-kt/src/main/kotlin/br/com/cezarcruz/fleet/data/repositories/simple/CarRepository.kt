package br.com.cezarcruz.fleet.data.repositories.simple

import br.com.cezarcruz.fleet.data.entities.CarEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface CarRepository : JpaRepository<CarEntity, Long> {
    fun findByPlate(plate: String): CarEntity?
    fun findAllByCategoryId(categoryId: Long): List<CarEntity>

    @Query(
        "SELECT c FROM CarEntity c " +
                "WHERE c.id NOT IN(SELECT DISTINCT r.car.id FROM RentEntity r WHERE r.endDate > ?1) " +
                "AND c.id = ?2"
    )
    fun findAvailable(startDate: LocalDate, carId: Long): CarEntity?
}
