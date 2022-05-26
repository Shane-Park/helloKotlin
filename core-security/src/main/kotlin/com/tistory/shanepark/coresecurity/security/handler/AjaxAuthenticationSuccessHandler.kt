package com.tistory.shanepark.coresecurity.security.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.tistory.shanepark.coresecurity.domain.Account
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AjaxAuthenticationSuccessHandler : AuthenticationSuccessHandler {

    private val objectMapper = ObjectMapper();
    private val log = LoggerFactory.getLogger(javaClass)

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication?,
    ) {
        val account = authentication?.principal as Account
        log.info("success handling:  $account")
        response.status = HttpStatus.OK.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        objectMapper.writeValue(response.writer, account)
    }

}
