package com.example.todo.model.http

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import javax.validation.Validation

class TodoDtoTest {

    val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest() {
        val todoDto = TodoDto().apply {
            this.title = "test"
            this.description = ""
            this.schedule = "2022-05-07 00:00:00"
        }
        val result = validator.validate(todoDto)
//        result.forEach{
//            println(it.propertyPath.last().name)
//            println(it.invalidValue)
//        }
        assertThat(result.isEmpty()).isTrue
    }

}
