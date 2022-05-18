package br.com.cezarcruz.fleet.config

import br.com.cezarcruz.fleet.aop.logging.LoggingAspect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@Configuration
@EnableAspectJAutoProxy
class LoggingAspectConfiguration {

    @Bean
    fun loggingAspect(): LoggingAspect {
        return LoggingAspect()
    }

}