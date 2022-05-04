package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping PutMethod"
    }

    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingObject(
        @Valid @RequestBody userRequest: UserRequest,
        bindingResult: BindingResult,
    ): Any {

        if (bindingResult.hasErrors()) {
            val msg = StringBuilder()
            bindingResult.allErrors.forEach{
                val field = it as FieldError
                val message = it.defaultMessage
                msg.append("${field.field} : $message\n")
            }
            return ResponseEntity.badRequest().body(msg.toString())
        }

        // 1. create userResponse
        return UserResponse().apply {
            // 2. create Result
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "SUCCESS"
            }
        }.apply {
            // 3. create description
            this.description = "detailed description"
        }.apply {
            // 3. create user mutable list
            val userList = mutableListOf<UserRequest>()
            userList.add(userRequest)
            userList.add(UserRequest().apply {
                this.name = "shane"
                this.age = 10
                this.email = "shane@java.com"
                this.address = "queenstown"
                this.phoneNumber = "123-4567"
            })
            this.users = userList
        }

    }

}
