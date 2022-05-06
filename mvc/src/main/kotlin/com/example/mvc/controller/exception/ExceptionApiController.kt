package com.example.mvc.controller.exception

import com.example.mvc.model.http.Error
import com.example.mvc.model.http.ErrorResponse
import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello(): String {
        val list = mutableListOf<String>()
        // val temp = list[0]
        return "hello"
    }

    @GetMapping("")
    fun get(
        @NotBlank @Size(min = 2, max = 6) @RequestParam name: String,
        @Min(10)
        @RequestParam age: Int,
    ): String {
        println("name = $name")
        println("age = $age")
        return "$name $age"
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolationException(
        e: ConstraintViolationException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        // 1. Analyze error
        val errors = mutableListOf<Error>()
        e.constraintViolations.forEach {
            errors.add(Error().apply {
                this.field = it.propertyPath.last().name
                this.message = it.message
                this.value = it.invalidValue
            })
        }

        // 2. make ErrorResponse
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "Error!"
            this.path = request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        // 3. return ResponseEntity
        return ResponseEntity.badRequest().body(errorResponse)
    }

    @PostMapping("")
    fun post(
        @Valid @RequestBody userRequest: UserRequest,
    ): UserRequest {
        println("userRequest = ${userRequest}")
        return userRequest
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(
        e: MethodArgumentNotValidException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()
        e.bindingResult.allErrors.forEach { errorObject ->
            val error = Error().apply {
                this.field = (errorObject as FieldError).field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue
            }
            errors.add(error)
        }

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "Error!"
            this.path = request.requestURI.toString()
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }

        return ResponseEntity.badRequest().body(errorResponse)
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun innerExceptionHandler(e: IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity.internalServerError().body("[inner Handler] Index out of bound")
    }
}
