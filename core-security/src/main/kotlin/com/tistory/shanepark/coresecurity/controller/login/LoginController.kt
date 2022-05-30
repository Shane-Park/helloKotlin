package com.tistory.shanepark.coresecurity.controller.login

import com.tistory.shanepark.coresecurity.domain.entity.Account
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
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
//        val authentication: Authentication? = SecurityContextHolder.getContext().authentication
//        if (authentication != null) {
//            SecurityContextLogoutHandler().logout(request, response, authentication)
//        }

        /**
         * SecurityContextLogoutHandler.logout doesn't use authentication, so I assigned null
         * @param authentication not used (can be <code>null</code>)
         * if then, doesn't need to get authentication from context, which is more efficient
         */
        SecurityContextLogoutHandler().logout(request, response, null)
        return "redirect:/login"
    }

    @GetMapping("/denied", "/api/denied")
    fun accessDenied(
        @RequestParam(value = "exception", required = false) exception: String?,
        model: Model,
    ): String {
        val authentication = SecurityContextHolder.getContext().authentication
        val user: Account = authentication.principal as Account
        model.addAttribute("username", user.username)
        model.addAttribute("exception", exception)
        return "user/login/denied"
    }

}
