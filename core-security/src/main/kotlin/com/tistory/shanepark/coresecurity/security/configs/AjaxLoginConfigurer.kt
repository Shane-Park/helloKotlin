package com.tistory.shanepark.coresecurity.security.configs

import com.tistory.shanepark.coresecurity.security.filter.AjaxLoginProcessingFilter
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.HttpSecurityBuilder
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.RememberMeServices
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher

class AjaxLoginConfigurer<H : HttpSecurityBuilder<H>?>(
    private val successHandler: AuthenticationSuccessHandler,
    private val failureHandler: AuthenticationFailureHandler,
    private val authenticationManager: AuthenticationManager,
) :
    AbstractAuthenticationFilterConfigurer<H, AjaxLoginConfigurer<H>, AjaxLoginProcessingFilter?>(
        AjaxLoginProcessingFilter(), null) {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun init(http: H) {
        super.init(http)
        log.info("AjaxLoginConfigurer.init")
    }

    override fun configure(http: H) {
        log.info("AjaxLoginConfigurer.configure started")
        authenticationFilter!!.setAuthenticationManager(authenticationManager)
        authenticationFilter!!.setAuthenticationSuccessHandler(successHandler)
        authenticationFilter!!.setAuthenticationFailureHandler(failureHandler)
        val sessionAuthenticationStrategy = http?.getSharedObject(SessionAuthenticationStrategy::class.java)
        if (sessionAuthenticationStrategy != null) {
            authenticationFilter!!.setSessionAuthenticationStrategy(sessionAuthenticationStrategy)
        }
        val rememberMeServices = http?.getSharedObject(RememberMeServices::class.java)
        if (rememberMeServices != null) {
            authenticationFilter!!.rememberMeServices = rememberMeServices
        }
        http?.setSharedObject(AjaxLoginProcessingFilter::class.java, authenticationFilter)
        http?.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    public override fun loginPage(loginPage: String): AjaxLoginConfigurer<H> {
        return super.loginPage(loginPage)
    }

    override fun createLoginProcessingUrlMatcher(loginProcessingUrl: String): RequestMatcher {
        return AntPathRequestMatcher(loginProcessingUrl, "POST")
    }
}
