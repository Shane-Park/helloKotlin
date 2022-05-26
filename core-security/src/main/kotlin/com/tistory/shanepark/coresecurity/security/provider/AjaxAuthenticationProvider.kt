package com.tistory.shanepark.coresecurity.security.provider

import com.tistory.shanepark.coresecurity.security.service.AccountContext
import com.tistory.shanepark.coresecurity.security.token.AjaxAuthenticationToken
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

class AjaxAuthenticationProvider(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder,
) : AuthenticationProvider {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials
        val accountContext: AccountContext = userDetailsService.loadUserByUsername(username) as AccountContext

        if (!passwordEncoder.matches(password as CharSequence?, accountContext.account.password)) {
            throw BadCredentialsException("BadCredentialException")
        }

        log.info("AJAX Log-in succeed, $authentication")

        return AjaxAuthenticationToken(accountContext.account, null, accountContext.authorities)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return AjaxAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
