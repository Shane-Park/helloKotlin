package com.example.mvc.controller.page

import com.example.mvc.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class PageController {

    @GetMapping("/main")
    fun main(): String {
        println("init Main")
        return "main.html"
    }

    @ResponseBody
    @GetMapping("/test")
    fun responseBody(): UserRequest {
        return UserRequest().apply {
            this.name = "shane"
        }
    }

}
