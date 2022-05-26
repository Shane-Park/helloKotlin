package com.tistory.shanepark.coresecurity.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AjaxAuthenticationFailureHandler : AuthenticationFailureHandler {

    private val objectMapper = ObjectMapper();
    private val log = LoggerFactory.getLogger(javaClass)

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException?,
    ) {
        var errorMessage = "Invalid Username or Password"

        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        if (exception is BadCredentialsException) {
            errorMessage = "Invalid Username or Password"
        } else if (exception is InsufficientAuthenticationException) {
            errorMessage = "Invalid Secret key"
            log.info("errorMessage = $errorMessage")
        }

        objectMapper.writeValue(response.writer, errorMessage)

    }

}
