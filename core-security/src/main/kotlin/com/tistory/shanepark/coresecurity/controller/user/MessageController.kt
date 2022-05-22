package com.tistory.shanepark.coresecurity.controller.user

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MessageController {
    @GetMapping(value = ["/messages"])
    fun messages(): String {
        return "user/messages"
    }
}
