package br.com.cezarcruz.fleet.web.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import javax.validation.constraints.Future
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class CreateRentRequest(

    @field:Positive
    @field:NotNull
    val car: Long?,

    @field:NotNull
    @field:Future
    @field:JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    val endDate: LocalDate?,

    @field:NotNull
    @field:Future
    @field:JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    val startDate: LocalDate?

)
