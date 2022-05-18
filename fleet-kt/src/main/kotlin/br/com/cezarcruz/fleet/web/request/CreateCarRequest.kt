package br.com.cezarcruz.fleet.web.request

import br.com.cezarcruz.fleet.web.validations.UniquePlate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class CreateCarRequest(

    @field:NotBlank
    @UniquePlate("placa já existe")
    val plate: String?,

    @field:NotBlank
    val model: String?,

    @field:Positive
    @field:NotNull
    val category: Long?

)