package br.com.cezarcruz.fleet.usecases

import br.com.cezarcruz.fleet.data.repositories.simple.CategoryRepository
import br.com.cezarcruz.fleet.mappers.CategoryMappers
import br.com.cezarcruz.fleet.web.response.CategoryResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GetCategoryUseCase(private val categoryRepository: CategoryRepository) {

    fun execute(category: Long): CategoryResponse? {
        return categoryRepository.findById(category)
            .map { CategoryMappers.from(it) }
            .orElse(null)
    }

    fun execute(pageable: Pageable): Page<CategoryResponse> {
        return categoryRepository.findAll(pageable).map {
            CategoryMappers.from(it)
        }
    }
}