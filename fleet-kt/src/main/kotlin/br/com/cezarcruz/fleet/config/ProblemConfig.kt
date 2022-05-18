package br.com.cezarcruz.fleet.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zalando.problem.ProblemModule
import org.zalando.problem.violations.ConstraintViolationProblemModule

@Configuration
class ProblemConfig {

    @Bean
    fun problemModule(): ProblemModule {
        return ProblemModule()
    }

    @Bean
    fun constraintViolationProblemModule(): ConstraintViolationProblemModule {
        return ConstraintViolationProblemModule()
    }

}