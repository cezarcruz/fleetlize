package br.com.cezarcruz.fleet.fixture

import br.com.cezarcruz.fleet.data.entities.CarEntity
import br.com.cezarcruz.fleet.web.request.CreateCarRequest

object CarFixture {

    fun createCarEntityWithCategory(): CarEntity {
        return CarEntity(
            id = 1L,
            plate = "CVY1234",
            model = "Ford Ka",
            category = CategoryFixture.createCategoryEntity()
        )
    }

    fun createCreateCarRequestWithoutCategory(): CreateCarRequest {
        return CreateCarRequest("CVY1234", "Ford Ka", null)
    }

    fun createValidCreateCarRequest(): CreateCarRequest {
        val createCarRequest = createCreateCarRequestWithoutCategory()
        return createCarRequest.copy(category = 1L)
    }
}