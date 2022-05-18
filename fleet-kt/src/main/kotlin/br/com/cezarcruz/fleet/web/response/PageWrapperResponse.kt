package br.com.cezarcruz.fleet.web.response

import org.springframework.data.domain.Page

class PageWrapperResponse<T>(
    private val page: Page<T>
) {
    val totalPages: Int = page.totalPages
    val totalElements: Long = page.totalElements
    val content: List<T> = page.content
}