package com.tistory.shanepark.coresecurity.controller.login

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class LoginController {

    @GetMapping("/login")
    fun login(): String {
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
}
