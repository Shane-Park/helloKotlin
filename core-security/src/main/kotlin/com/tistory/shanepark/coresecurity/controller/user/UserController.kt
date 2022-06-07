package com.tistory.shanepark.coresecurity.controller.user

import com.tistory.shanepark.coresecurity.domain.entity.Account
import com.tistory.shanepark.coresecurity.domain.dto.AccountDto
import com.tistory.shanepark.coresecurity.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.security.Principal

@Controller
class UserController(
    private val passwordEncoder: PasswordEncoder,
    private val userService: UserService,
) {
    @GetMapping(value = ["/mypage"])
    @Throws(Exception::class)
    fun myPage(authentication: Authentication?, principal: Principal?): String {
        userService. order();
        return "user/mypage"
    }

    @GetMapping("/users")
    fun createUser(): String {
        return "user/login/register"
    }

    @PostMapping("/users")
    fun createUser(accountDto: AccountDto): String {
        val account: Account = Account.fromDto(accountDto)
        account.password = passwordEncoder.encode(account.password)
        userService.createUser(account)

        return "redirect:/"
    }
}
