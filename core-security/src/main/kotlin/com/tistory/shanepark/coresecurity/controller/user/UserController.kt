package com.tistory.shanepark.coresecurity.controller.user

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

@Controller
class UserController {
    @GetMapping(value = ["/mypage"])
    @Throws(Exception::class)
    fun myPage(authentication: Authentication?, principal: Principal?): String {
        return "user/mypage"
    }
}
