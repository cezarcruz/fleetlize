package br.com.cezarcruz.fleet.usecases

import br.com.cezarcruz.fleet.data.repositories.simple.CategoryRepository
import br.com.cezarcruz.fleet.mappers.CategoryMappers
import br.com.cezarcruz.fleet.web.request.CreateCategoryRequest
import br.com.cezarcruz.fleet.web.response.CategoryResponse
import org.springframework.stereotype.Service

@Service
class CreateCategoryUseCase(private val categoryRepository: CategoryRepository) {
    fun execute(createCategoryRequest: CreateCategoryRequest): CategoryResponse {
        return categoryRepository.save(CategoryMappers.from(createCategoryRequest)).let {
            CategoryMappers.from(it)
        }
    }
}
