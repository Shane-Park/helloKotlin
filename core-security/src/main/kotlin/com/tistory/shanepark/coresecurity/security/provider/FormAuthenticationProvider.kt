package com.tistory.shanepark.coresecurity.security.provider

import com.tistory.shanepark.coresecurity.security.common.FormWebAuthenticationDetails
import com.tistory.shanepark.coresecurity.service.AccountContext
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

class FormAuthenticationProvider(
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

        val formWebAuthenticationDetails: FormWebAuthenticationDetails =
            authentication.details as FormWebAuthenticationDetails
        val secretKey = formWebAuthenticationDetails.secretKey

        if ("secret" != secretKey) {
            log.info("Secret key is not valid. secretKey=$secretKey")
            /**
             * but even if it throws Exception, it succeeds login process
             * the difference is if it throws InsufficientException, authentication.principal is userDetails.User
             * if not, principal is Account
             */
            throw InsufficientAuthenticationException("InsufficientAuthenticationException")
        }

        return UsernamePasswordAuthenticationToken(accountContext.account, null, accountContext.authorities)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
