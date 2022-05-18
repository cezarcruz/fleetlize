package br.com.cezarcruz.fleet.usecases

import br.com.cezarcruz.fleet.data.entities.CarEntity
import br.com.cezarcruz.fleet.data.repositories.simple.CarRepository
import br.com.cezarcruz.fleet.fixture.CarFixture
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.streams.toList

@ExtendWith(SpringExtension::class)
class GetCarUseCaseTest {


    @MockK
    private lateinit var carRepository: CarRepository

    @InjectMockKs
    private lateinit var getCarUseCase: GetCarUseCase

    @Test
    fun `should get a list of cars`() {
        val carEntity = CarFixture.createCarEntityWithCategory()
        every {
            carRepository.findAll(Pageable.unpaged())
        } returns PageImpl<CarEntity>(arrayListOf(carEntity))

        val list = getCarUseCase.execute(Pageable.unpaged()).get().toList()

        assertThat(list).isNotNull

        assertThat(list).isNotEmpty

        val carDomain = list.get(0)
        assertThat(carDomain).isNotNull
        assertThat(carDomain.category).isNotNull
        assertThat(carDomain.plate).isEqualTo(carEntity.plate)
        assertThat(carDomain.model).isEqualTo(carEntity.model)

        verify(exactly = 1) { carRepository.findAll(Pageable.unpaged()) }

    }

    @Test
    fun `should get a car by plate`() {
        val carEntity = CarFixture.createCarEntityWithCategory()
        val plate = "CVY1234"

        every {
            carRepository.findByPlate(plate)
        } returns carEntity

        val carDomain = getCarUseCase.execute(plate)!!

        assertThat(carDomain).isNotNull
        assertThat(carDomain.category).isNotNull
        assertThat(carDomain.plate).isEqualTo(carEntity.plate)
        assertThat(carDomain.model).isEqualTo(carEntity.model)

        verify(exactly = 1) { carRepository.findByPlate(plate) }

    }

    @Test
    fun `should not find a car by plate`() {
        val plate = "CVY1234"

        every {
            carRepository.findByPlate(plate)
        } returns null

        val carDomain = getCarUseCase.execute(plate)

        assertThat(carDomain).isNull()

        verify(exactly = 1) { carRepository.findByPlate(plate) }

    }

}