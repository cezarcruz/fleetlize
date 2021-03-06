package br.com.cezarcruz.fleet.data.repositories.simple

import br.com.cezarcruz.fleet.data.entities.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<CategoryEntity, Long> {

}
