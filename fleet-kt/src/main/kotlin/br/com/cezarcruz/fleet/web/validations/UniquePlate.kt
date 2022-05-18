package br.com.cezarcruz.fleet.web.validations

import br.com.cezarcruz.fleet.web.validations.impl.UniquePlateValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FIELD,
)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniquePlateValidator::class])
annotation class UniquePlate(
    val message: String = "{javax.validation.constraints.NotBlank.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
