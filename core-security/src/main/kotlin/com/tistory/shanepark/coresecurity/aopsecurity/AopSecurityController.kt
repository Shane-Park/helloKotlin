package com.tistory.shanepark.coresecurity.aopsecurity

import com.tistory.shanepark.coresecurity.domain.dto.AccountDto
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

@Controller
class AopSecurityController {

    @GetMapping("/preAuthorize")
    @PreAuthorize("hasRole('ROLE_USER') and #account.username == principal.name")
    fun preAuthorize(
        account: AccountDto?,
        model: Model,
        principal: Principal?,
    ): String {
        model.addAttribute("method", "Success @PreAuthorize")
        return "aop/method"
    }

}
