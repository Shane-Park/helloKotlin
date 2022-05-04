package com.example.mvc.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api")
@Validated
class DeleteApiController {

    // 1. Query Parameter
    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
        @RequestParam(value = "name") _name: String,
        @NotNull(message = "age is required")
        @Min(value = 20, message = "age must be greater than 20")
        @RequestParam(name = "age") _age: Int,
    ): String {
        println("name = $_name")
        println("age = $_age")
        return "$_name $_age"
    }

    // 2. Path Variable
    @DeleteMapping(path = ["/delete-mapping/{name}/{age}"])
    fun deleteMappingPath(
        @NotNull @Size(min = 2, max = 5, message = "length of name should be between 2 to 5")
        @PathVariable("name") _name: String,
        @NotNull(message = "age is required") @Min(value = 20, message = "age must be greater than 20")
        @PathVariable("age") _age: Int,
    ): String {
        println("name = $_name")
        println("age = $_age")
        return "$_name $_age"
    }

}
