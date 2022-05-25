package com.tistory.shanepark.coresecurity.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.tistory.shanepark.coresecurity.domain.AccountDto
import com.tistory.shanepark.coresecurity.security.token.AjaxAuthenticationToken
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.thymeleaf.util.StringUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AjaxLoginProcessingFilter : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher("/api/login")) {

    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        if (!isAjax(request)) {
            throw IllegalStateException("Authentication is not supported")
        }
        val accountDto = objectMapper.readValue(request?.reader, AccountDto::class.java)
        log.info("accountDto: $accountDto")

        if (StringUtils.isEmpty(accountDto.username) || StringUtils.isEmpty(accountDto.password)) {
            throw IllegalArgumentException("Username or Password is empty")
        }

        val ajaxAuthenticationToken = AjaxAuthenticationToken(accountDto.username, accountDto.password)

        return authenticationManager.authenticate(ajaxAuthenticationToken)
    }

    private fun isAjax(request: HttpServletRequest?): Boolean {
        return "XMLHttpRequest" == request?.getHeader("X-Requested-With")
    }
}
