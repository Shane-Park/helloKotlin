package com.tistory.shanepark.coresecurity.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping(value = ["/"])
    @Throws(Exception::class)
    fun home(): String {
        return "home"
    }
}
