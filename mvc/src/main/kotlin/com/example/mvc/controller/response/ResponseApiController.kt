package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // 1. Get 400
    // (GET) localhost:8080/api/response
    @GetMapping
    fun getMapping(@RequestParam(required = false) age: Int?): ResponseEntity<String> {
//        // 1. if age == null, 400 Error
//        if (age == null) {
//            return ResponseEntity.badRequest().body("age is required");
//        }
//        // 2. if age < 20, 400 Error
//        if (age < 20) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("age should be greater than 20. Your age: $age")
//        }

        age?.let {
            // age is not null
            if (age < 20) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("age should be greater than 20. Your age: $age")
            }
        } ?: kotlin.run {
            // age == null
            return ResponseEntity.badRequest().body("age is required");
        }


        return ResponseEntity.ok("OK")
    }

    // 2. Post 200
    @PostMapping
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(userRequest)
    }

    // 3. Put 201
    @PutMapping
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        // 1. create new one because didn't have data
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 4. Delete 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: String): ResponseEntity<Any> {
        return ResponseEntity.internalServerError().build()
    }

}
