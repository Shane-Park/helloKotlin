package com.tistory.shanepark.coresecurity.security.service

import com.tistory.shanepark.coresecurity.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("UserDetailsService")
class CustomUserDetailsService(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val account = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("username not found")

        val roles = mutableListOf<GrantedAuthority>()
        roles.add(SimpleGrantedAuthority(account.role))

        return AccountContext(account, roles)

    }
}
