package com.tistory.shanepark.coresecurity.controller.user

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MessageController {
    @GetMapping(value = ["/messages"])
    fun messages(): String {
        return "user/messages"
    }

    @GetMapping("/api/messages")
    @ResponseBody
    fun apiMessage(): String {
        return "messages ok"
    }
}
