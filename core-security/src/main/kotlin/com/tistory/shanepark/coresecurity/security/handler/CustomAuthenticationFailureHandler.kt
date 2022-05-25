package com.tistory.shanepark.coresecurity.security.handler

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationFailureHandler : SimpleUrlAuthenticationFailureHandler() {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?,
    ) {
        var errorMessage = "Invalid Username or Password"

        if (exception is BadCredentialsException) {
            errorMessage = "Invalid Username or Password"
        } else if (exception is InsufficientAuthenticationException) {
            errorMessage = "Invalid Secret key"
            log.info("errorMessage = $errorMessage")
        }

        setDefaultFailureUrl("/login?error=true&exception=$errorMessage")
        super.onAuthenticationFailure(request, response, exception)
    }
}
