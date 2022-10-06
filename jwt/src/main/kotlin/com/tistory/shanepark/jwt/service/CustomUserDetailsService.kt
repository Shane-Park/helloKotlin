package com.tistory.shanepark.jwt.service

import com.tistory.shanepark.jwt.entity.User
import com.tistory.shanepark.jwt.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        userRepository.findOneWithAuthoritiesByUsername(username)
            .orElseThrow { RuntimeException("User not found") }
            .let { return createUser(username, it) }
    }

    private fun createUser(username: String, user: User): UserDetails {
        if (user.activated == false) {
            throw RuntimeException("User not activated")
        }
        user.authorities.let { it ->
            return org.springframework.security.core.userdetails.User(
                username,
                user.password,
                it.map { SimpleGrantedAuthority(it.authorityName) }.toList()
            )
        }
    }
}
