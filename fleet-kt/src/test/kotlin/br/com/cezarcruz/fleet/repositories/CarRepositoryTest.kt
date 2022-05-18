package br.com.cezarcruz.fleet.repositories

import br.com.cezarcruz.fleet.data.repositories.simple.CarRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class CarRepositoryTest(
    @Autowired private val carRepository: CarRepository
) {

    @Test
    fun `should get car by plate`() {
        val plate = "CVY1239"

        val carEntity =
            carRepository.findByPlate(plate)

        carEntity?.let {
            assertThat(it.plate).isNotNull
            assertThat(it.plate).isEqualTo(plate)
            assertThat(it.category).isNotNull
        } ?: throw AssertionError("nao encontrou carro com placa $plate")

    }

}