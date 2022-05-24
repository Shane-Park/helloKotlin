package com.tistory.shanepark.coresecurity.security.provider

import com.tistory.shanepark.coresecurity.security.service.AccountContext
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

class CustomAuthenticationProvider(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder,
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials
        val accountContext: AccountContext = userDetailsService.loadUserByUsername(username) as AccountContext
        if (!passwordEncoder.matches(password as CharSequence?, accountContext.account.password)) {
            throw BadCredentialsException("BadCredentialException")
        }
        return UsernamePasswordAuthenticationToken(accountContext.account, null, accountContext.authorities)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
