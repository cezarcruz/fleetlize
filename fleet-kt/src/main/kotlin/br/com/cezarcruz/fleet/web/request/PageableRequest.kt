package br.com.cezarcruz.fleet.web.request

import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.RequestParam

class PageableRequest(

    @RequestParam(name = "size", defaultValue = "20", required = false)
    val size: Int = 20,

    @RequestParam(name = "page", defaultValue = "0", required = false)
    val page: Int = 0
)

fun PageableRequest.toPageable() = PageRequest.of(page, size)