package br.com.cezarcruz.fleet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@SpringBootApplication(
    exclude = [
        ErrorMvcAutoConfiguration::class
    ]
)
@EnableSwagger2
@EnableCaching
class FleetApplication

fun main(args: Array<String>) {
    runApplication<FleetApplication>(*args)
}
