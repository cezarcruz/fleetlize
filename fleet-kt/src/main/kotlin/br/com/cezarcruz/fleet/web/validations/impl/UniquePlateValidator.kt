package br.com.cezarcruz.fleet.web.validations.impl

import br.com.cezarcruz.fleet.data.repositories.simple.CarRepository
import br.com.cezarcruz.fleet.web.validations.UniquePlate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class UniquePlateValidator(private val carRepository: CarRepository) :
    ConstraintValidator<UniquePlate, String> {

    val log: Logger = LoggerFactory.getLogger(UniquePlateValidator::class.java)

    @Cacheable(value = ["plates-exists"], key = "#value", unless = "#result == true", condition = "#value != null")
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {

        log.debug("validando placa existente: $value")

        return if (value == null) {
            true
        } else {
            carRepository.findByPlate(value) == null
        }
    }

}