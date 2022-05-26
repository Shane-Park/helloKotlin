package com.tistory.shanepark.coresecurity.security.handler

import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AjaxAccessDeniedHandler : AccessDeniedHandler {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?,
    ) {
        log.info("AjaxAccessDeniedHandler.handle")
        response?.sendError(HttpServletResponse.SC_FORBIDDEN, "Access is denied")
    }
}
