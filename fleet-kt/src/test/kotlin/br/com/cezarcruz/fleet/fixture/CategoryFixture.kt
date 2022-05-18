package br.com.cezarcruz.fleet.fixture

import br.com.cezarcruz.fleet.data.entities.CategoryEntity
import br.com.cezarcruz.fleet.web.response.CategoryResponse
import java.math.BigDecimal

object CategoryFixture {
    fun createCategoryEntity(): CategoryEntity {
        return CategoryEntity(id = 1L, name = "Premium Plus", price = BigDecimal.TEN)
    }

    fun createCategoryEntityWithoutId(): CategoryEntity {
        val categoryEntity = createCategoryEntity()

        categoryEntity.id = null

        return categoryEntity
    }

    fun createCategoryDomain(): CategoryResponse {
        return CategoryResponse(1L, "Premium Plus", BigDecimal.TEN)
    }
}