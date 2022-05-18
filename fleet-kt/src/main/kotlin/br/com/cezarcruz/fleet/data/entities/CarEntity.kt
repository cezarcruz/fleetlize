package br.com.cezarcruz.fleet.data.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "car")
class CarEntity(

    @Column(unique = true)
    var plate: String,

    var model: String,

    @OneToOne
    var category: CategoryEntity,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?

) {
    companion object {
        fun newEntity(plate: String, model: String, category: CategoryEntity) =
            CarEntity(plate = plate, model = model, category = category, id = null)
    }
}
