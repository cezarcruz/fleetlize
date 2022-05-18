package br.com.cezarcruz.fleet.web.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDate

data class RentResponse(
    val car: CarResponse,

    @field:JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    val startDate: LocalDate,

    @field:JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    val endDate: LocalDate,
    val days: Long,
    val totalPrice: BigDecimal
)
