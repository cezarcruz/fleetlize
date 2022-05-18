package br.com.cezarcruz.fleet.web

import br.com.cezarcruz.fleet.usecases.CreateCategoryUseCase
import br.com.cezarcruz.fleet.usecases.GetCategoryUseCase
import br.com.cezarcruz.fleet.web.request.CreateCategoryRequest
import br.com.cezarcruz.fleet.web.response.CategoryResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.math.BigDecimal

@WebMvcTest(CategoryController::class)
@ExtendWith(SpringExtension::class)
class CategoryControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val mapper: ObjectMapper
) {

    @MockkBean
    private lateinit var getCategoryUseCase: GetCategoryUseCase

    @MockkBean
    private lateinit var createCategoryUseCase: CreateCategoryUseCase

    @Test
    fun `should create a category`() {

        every {
            createCategoryUseCase.execute(any())
        } returns CategoryResponse(
            id = 1L,
            name = "Premium Plus Advanced",
            price = BigDecimal.TEN
        )

        val categoryRequest = CreateCategoryRequest("Premium Plus Advanced", BigDecimal.TEN)

        mockMvc.post("/category") {
            contentType = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(categoryRequest)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.name") { value("Premium Plus Advanced") }
            jsonPath("$.price") { value(10) }
        }

        verify(exactly = 1) { createCategoryUseCase.execute(any()) }


    }
}
