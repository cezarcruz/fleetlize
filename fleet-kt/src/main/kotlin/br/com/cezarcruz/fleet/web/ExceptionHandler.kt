package br.com.cezarcruz.fleet.web

import br.com.cezarcruz.fleet.web.errors.FieldErrorVM
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.NativeWebRequest
import org.zalando.problem.Problem
import org.zalando.problem.spring.web.advice.ProblemHandling

@ControllerAdvice
class ExceptionHandler : ProblemHandling {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        request: NativeWebRequest
    ): ResponseEntity<Problem> {
        val result: BindingResult = ex.bindingResult

        val fieldErrors = result.fieldErrors.map {
            FieldErrorVM(
                it.objectName, it.field, it.code!!
            )
        }

        val problem: Problem = Problem.builder()
            .withTitle("Method argument not valid")
            .withStatus(defaultConstraintViolationStatus())
            .with("message", "error.validation")
            .with("fieldErrors", fieldErrors)
            .build()

        return create(ex, problem, request)

    }

}
