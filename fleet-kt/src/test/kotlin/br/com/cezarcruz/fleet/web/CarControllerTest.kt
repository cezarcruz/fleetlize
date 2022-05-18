package br.com.cezarcruz.fleet.web

import br.com.cezarcruz.fleet.data.repositories.simple.CarRepository
import br.com.cezarcruz.fleet.fixture.CarFixture
import br.com.cezarcruz.fleet.usecases.CreateCarUseCase
import br.com.cezarcruz.fleet.usecases.GetCarUseCase
import br.com.cezarcruz.fleet.usecases.GetCategoryUseCase
import br.com.cezarcruz.fleet.web.request.CreateCarRequest
import br.com.cezarcruz.fleet.web.response.CarResponse
import br.com.cezarcruz.fleet.web.response.CategoryResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.math.BigDecimal

@WebMvcTest(CarController::class)
@ExtendWith(SpringExtension::class)
class CarControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val mapper: ObjectMapper
) {

    @MockkBean
    private lateinit var getCarUseCase: GetCarUseCase

    @MockkBean
    private lateinit var createCarUseCase: CreateCarUseCase

    @MockkBean
    private lateinit var getCategoryUseCase: GetCategoryUseCase

    @MockkBean
    private lateinit var carRepository: CarRepository

    @Test
    fun `should create a car`() {

        val categoryDomain = CategoryResponse(
            id = 1L,
            name = "Premium Plus",
            price = BigDecimal.TEN
        )

        every {
            getCategoryUseCase.execute(1L)
        } returns categoryDomain

        every {
            createCarUseCase.execute(any())
        } returns CarResponse(
            id = 1L,
            plate = "CVY1234",
            model = "Ford Ka",
            category = categoryDomain
        )

        every {
            carRepository.findByPlate("CVY1234")
        } returns null

        val createCarRequest = CreateCarRequest(
            plate = "CVY1234",
            model = "Ford Ka",
            category = 1L
        )

        mockMvc.post("/car") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(createCarRequest)
        }.andDo {
            print()
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.plate") { value("CVY1234") }
            jsonPath("$.model") { value("Ford Ka") }
            jsonPath("$.category") { exists() }
        }

        verify(exactly = 1) { carRepository.findByPlate("CVY1234") }
        verify(exactly = 1) { getCategoryUseCase.execute(1L) }
        verify(exactly = 1) { createCarUseCase.execute(any()) }

    }

    @Test
    fun `should create a car without category`() {

        every {
            carRepository.findByPlate(any())
        } returns null

        mockMvc.post("/car") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(CarFixture.createCreateCarRequestWithoutCategory())
        }.andDo {
            print()
        }.andExpect {
            status { isBadRequest() }
            content { contentType(MediaType.APPLICATION_PROBLEM_JSON) }
        }

    }

    @Test
    fun `should not create a car that already exists`() {
        val categoryDomain = CategoryResponse(
            id = 1L,
            name = "Premium Plus",
            price = BigDecimal.TEN
        )

        every {
            getCategoryUseCase.execute(1L)
        } returns categoryDomain

        every {
            createCarUseCase.execute(any())
        } returns CarResponse(
            plate = "CVY1234",
            model = "Ford Ka",
            category = categoryDomain,
            id = 1L
        )

        every {
            carRepository.findByPlate("CVY1234")
        } returns CarFixture.createCarEntityWithCategory()

        val createCarRequest = CreateCarRequest(
            plate = "CVY1234",
            model = "Ford Ka",
            category = 1L
        )

        mockMvc.post("/car") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(createCarRequest)
        }.andDo {
            print()
        }.andExpect {
            status { isBadRequest() }
            content { contentType(MediaType.APPLICATION_PROBLEM_JSON) }
        }

        verify(exactly = 1) { carRepository.findByPlate("CVY1234") }
        verify(exactly = 0) { getCategoryUseCase.execute(1L) }
        verify(exactly = 0) { createCarUseCase.execute(any()) }
    }

    @Test
    @Disabled("cache is not working here, why?")
    fun `should not create a car that already exists and use cache`() {
        val categoryDomain = CategoryResponse(
            id = 1L,
            name = "Premium Plus",
            price = BigDecimal.TEN
        )

        every {
            getCategoryUseCase.execute(1L)
        } returns categoryDomain

        every {
            createCarUseCase.execute(any())
        } returns CarResponse(
            plate = "CVY1234",
            model = "Ford Ka",
            category = categoryDomain,
            id = 1L
        )

        every {
            carRepository.findByPlate("CVY1234")
        } returns CarFixture.createCarEntityWithCategory()

        val createCarRequest = CreateCarRequest(
            plate = "CVY1234",
            model = "Ford Ka",
            category = 1L
        )

        mockMvc.post("/car") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(createCarRequest)
        }.andDo {
            print()
        }.andExpect {
            status { isBadRequest() }
            content { contentType(MediaType.APPLICATION_PROBLEM_JSON) }
        }

        mockMvc.post("/car") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(createCarRequest)
        }.andDo {
            print()
        }.andExpect {
            status { isBadRequest() }
            content { contentType(MediaType.APPLICATION_PROBLEM_JSON) }
        }

        verify(exactly = 1) { carRepository.findByPlate("CVY1234") }
        verify(exactly = 0) { getCategoryUseCase.execute(1L) }
        verify(exactly = 0) { createCarUseCase.execute(any()) }
    }
}