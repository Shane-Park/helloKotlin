package com.tistory.shanepark.coresecurity.controller.user

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MessageController {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping(value = ["/messages"])
    fun messages(): String {
        return "user/messages"
    }

    @PostMapping("/api/messages")
    @ResponseBody
    fun apiMessage(): String {
        log.info("/api/messages controller")
        return "\"message ok\""
    }

}
