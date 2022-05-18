package br.com.cezarcruz.fleet.usecases

import br.com.cezarcruz.fleet.data.entities.CarEntity
import br.com.cezarcruz.fleet.data.entities.CategoryEntity
import br.com.cezarcruz.fleet.data.repositories.simple.CarRepository
import br.com.cezarcruz.fleet.data.repositories.simple.CategoryRepository
import br.com.cezarcruz.fleet.fixture.CarFixture
import br.com.cezarcruz.fleet.fixture.CategoryFixture
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
class CreateCarUseCaseTest {

    @MockK
    private lateinit var carRepository: CarRepository

    @MockK
    private lateinit var categoryRepository: CategoryRepository

    @InjectMockKs
    private lateinit var createCarUseCase: CreateCarUseCase

    @Test
    fun `should create a car`() {

        every {
            categoryRepository.getOne(any())
        } returns CategoryFixture.createCategoryEntity()

        every {
            carRepository.saveAndFlush(any())
        } returns CarEntity(
            id = 1L,
            model = "",
            plate = "",
            category = CategoryEntity(id = 1L, name = "", price = BigDecimal.TEN)
        )

        val execute = createCarUseCase.execute(CarFixture.createValidCreateCarRequest())

        assertThat(execute).isNotNull
        assertThat(execute.plate).isEmpty()

        verify(exactly = 1) {
            carRepository.saveAndFlush(any())
        }

        verify(exactly = 1) {
            categoryRepository.getOne(any())
        }
    }

}