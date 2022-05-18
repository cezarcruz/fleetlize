package br.com.cezarcruz.fleet.data.entities

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "category")
class CategoryEntity(
    var name: String,
    var price: BigDecimal,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?
)