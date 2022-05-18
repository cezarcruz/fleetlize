package br.com.cezarcruz.fleet.web.request.filters

import java.time.LocalDate

data class RentFilter(
    val car: Long?,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val id: Long?
)
