package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController // restapi controller
@RequestMapping("/api") // http://localhost:8080/api
class GetApiController {

    @GetMapping("/hello") // (GET) http://localhost:8080/api/hello
    fun hello(): String {
        return "Hello Kotlin"
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}") // (GET) http://localhost:8080/api/get-mapping/path-variable/shane
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
        println("${name}, $age ")
        return "$name $age"
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}") // (GET) http://localhost:8080/api/get-mapping/path-variable/shane
    fun pathVariable2(@PathVariable(value = "name") _name: String, @PathVariable(name = "age") age: Int): String {
        val name = "hello"

        println("${_name}, $age ")
        return "$_name $age"
    }

    // QueryParameter
    @GetMapping("/get-mapping/query-param")
    fun queryParam(
        @RequestParam name: String,
        @RequestParam age: Int,
    ): String {
        println("$name, $age")
        return "$name $age"
    }

    // name, age, address, email
    @GetMapping("/get-mapping/query-param/object")
    fun queryParameterObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        return map
    }

}
