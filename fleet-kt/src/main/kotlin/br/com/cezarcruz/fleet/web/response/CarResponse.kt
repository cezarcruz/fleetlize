package br.com.cezarcruz.fleet.web.response

data class CarResponse(
    val id: Long,
    val plate: String,
    val model: String,
    val category: CategoryResponse
)