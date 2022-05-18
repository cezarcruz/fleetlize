package br.com.cezarcruz.fleet.mappers

import br.com.cezarcruz.fleet.data.entities.CarEntity
import br.com.cezarcruz.fleet.data.entities.RentEntity
import br.com.cezarcruz.fleet.web.request.CreateRentRequest
import br.com.cezarcruz.fleet.web.response.RentResponse
import java.math.BigDecimal
import java.time.LocalDate

object RentMappers {

    fun from(
        createRentRequest: CreateRentRequest,
        carEntity: CarEntity,
        totalPrice: BigDecimal
    ) = RentEntity.newEntity(
        carEntity,
        createRentRequest.startDate as LocalDate,
        createRentRequest.endDate as LocalDate,
        totalPrice
    )

    fun from(rentEntity: RentEntity) = RentResponse(
        CarMappers.from(rentEntity.car),
        rentEntity.startDate,
        rentEntity.endDate,
        rentEntity.days,
        rentEntity.totalPrice
    )

}