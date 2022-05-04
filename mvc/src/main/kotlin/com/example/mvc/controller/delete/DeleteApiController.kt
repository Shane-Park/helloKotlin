package com.example.mvc.controller.delete

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class DeleteApiController {

    // 1. Query Parameter
    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
        @RequestParam(value = "name") _name: String,
        @RequestParam(name = "age") _age: Int,
    ): String {
        println("name = $_name")
        println("age = $_age")
        return "$_name $_age"
    }

    // 2. Path Variable
    @DeleteMapping(path = ["/delete-mapping/{name}/{age}"])
    fun deleteMappingPath(
        @PathVariable("name") _name: String,
        @PathVariable("age") _age: Int,
    ): String {
        println("name = $_name")
        println("age = $_age")
        return "$_name $_age"
    }

}
