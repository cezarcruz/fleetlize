package br.com.cezarcruz.fleet.web.request

import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class CreateCategoryRequest(

    @field:NotEmpty
    var name: String?,

    @field:Positive
    @field:NotNull
    var price: BigDecimal?
)
