package br.com.cezarcruz.fleet.mappers

import br.com.cezarcruz.fleet.data.entities.CategoryEntity
import br.com.cezarcruz.fleet.web.request.CreateCategoryRequest
import br.com.cezarcruz.fleet.web.response.CategoryResponse
import java.math.BigDecimal

object CategoryMappers {

    fun from(categoryEntity: CategoryEntity) = CategoryResponse(
        id = categoryEntity.id!!,
        name = categoryEntity.name,
        price = categoryEntity.price
    )

    fun from(createCategoryRequest: CreateCategoryRequest) = CategoryEntity(
        name = createCategoryRequest.name as String,
        price = createCategoryRequest.price as BigDecimal,
        id = null
    )


}