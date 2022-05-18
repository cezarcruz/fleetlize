package br.com.cezarcruz.fleet.mappers

import br.com.cezarcruz.fleet.data.entities.CarEntity
import br.com.cezarcruz.fleet.data.entities.CategoryEntity
import br.com.cezarcruz.fleet.web.request.CreateCarRequest
import br.com.cezarcruz.fleet.web.response.CarResponse

object CarMappers {

    fun from(carEntity: CarEntity) = CarResponse(
        id = carEntity.id as Long,
        plate = carEntity.plate,
        model = carEntity.model,
        category = CategoryMappers.from(carEntity.category)
    )


    fun from(createCarRequest: CreateCarRequest, categoryEntity: CategoryEntity) =
        CarEntity.newEntity(
            plate = createCarRequest.plate as String,
            model = createCarRequest.model as String,
            category = categoryEntity
        )

}