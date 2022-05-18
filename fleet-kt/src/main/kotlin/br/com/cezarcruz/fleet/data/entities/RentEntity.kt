package br.com.cezarcruz.fleet.data.entities

import java.math.BigDecimal
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "rent")
class RentEntity(

    @OneToOne
    var car: CarEntity,

    @Column(name = "start_date")
    var startDate: LocalDate,

    @Column(name = "end_date")
    var endDate: LocalDate,

    @Column(name = "total_price")
    var totalPrice: BigDecimal,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?
) {

    val days = startDate.until(endDate, ChronoUnit.DAYS)

    companion object {
        fun newEntity(
            car: CarEntity,
            startDate: LocalDate,
            endDate: LocalDate,
            totalPrice: BigDecimal
        ): RentEntity {
            return RentEntity(car, startDate, endDate, totalPrice, null)
        }
    }
}
