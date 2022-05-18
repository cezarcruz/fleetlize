package br.com.cezarcruz.fleet.web.response

import java.math.BigDecimal

data class CategoryResponse(
    val id: Long,
    val name: String,
    val price: BigDecimal
)