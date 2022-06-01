package com.tistory.shanepark.coresecurity.controller.login

import com.tistory.shanepark.coresecurity.domain.entity.Account
import com.tistory.shanepark.coresecurity.security.token.AjaxAuthenticationToken
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.security.Principal
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class LoginController {

    @GetMapping("/login", "/api/login")
    fun login(
        @RequestParam(value = "error", required = false) error: String?,
        @RequestParam(value = "exception", required = false) exception: String?,
        model: Model,
    ): String {
        model.addAttribute("error", error)
        model.addAttribute("exception", exception)
        return "login"
    }

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null) {
            SecurityContextLogoutHandler().logout(request, response, authentication)
        }
        return "redirect:/login"
    }

    @GetMapping("/denied", "/api/denied")
    fun accessDenied(
        @RequestParam(value = "exception", required = false) exception: String?,
        principal: Principal,
        model: Model,
    ): String {
        var account: Account? = null

        if (principal is UsernamePasswordAuthenticationToken) {
            account = principal.principal as Account
        } else if (principal is AjaxAuthenticationToken) {
            account = principal.principal as Account
        }

        model.addAttribute("username", account?.username)
        model.addAttribute("exception", exception)

        return "user/login/denied"
    }

}
