package io.security.basicsecurity

import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class SecurityController {

    @GetMapping("/")
    fun index(session: HttpSession): String {

        val authentication = SecurityContextHolder.getContext().authentication
        val context: SecurityContext? =
            session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) as? SecurityContext

        // it is true when user is logged-in
        println("attribute == authentication = ${context?.authentication == authentication}")

        return "home"
    }

    @GetMapping("/thread")
    fun thread(): String {
        Thread {
            val authentication = SecurityContextHolder.getContext().authentication

            println("authentication = $authentication") // it is null because it's children Thread
            /**
             * but if you set strategy as MODE_INHERITABLETHREADLOCAL on configure like below
             * SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL)
             * it shares even on child Threads.
             */

        }.start()
        return "thread"
    }

    @GetMapping("loginPage")
    fun loginPage(): String {
        return "loginPage"
    }

    @GetMapping("/user")
    fun user(): String {
        return "user"
    }

    @GetMapping("/admin/pay")
    fun adminPay(): String {
        return "adminPay"
    }

    @GetMapping("/admin/**")
    fun admin(): String {
        return "admin"
    }

    @GetMapping("/denied")
    fun denied(): String {
        return "Access denied"
    }

    @GetMapping("/login")
    fun login(): String {
        return "Access denied"
    }

}
