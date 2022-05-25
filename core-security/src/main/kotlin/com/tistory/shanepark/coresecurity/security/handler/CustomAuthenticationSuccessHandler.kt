package com.tistory.shanepark.coresecurity.security.handler

import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.SavedRequest
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {

    private val requestCache = HttpSessionRequestCache()
    private val redirectStrategy = DefaultRedirectStrategy()
    private val log = LoggerFactory.getLogger(javaClass)

    init {
        defaultTargetUrl = "/"
    }

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?,
    ) {

        val savedRequest: SavedRequest? = requestCache.getRequest(request, response)
        if (savedRequest != null) {
            val targetUrl = savedRequest.redirectUrl
            log.info("targetUrl : {}", targetUrl)
            redirectStrategy.sendRedirect(request, response, targetUrl)
        } else {
            redirectStrategy.sendRedirect(request, response, defaultTargetUrl)
        }
    }

}
