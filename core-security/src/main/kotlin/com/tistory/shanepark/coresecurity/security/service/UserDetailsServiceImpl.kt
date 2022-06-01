package com.tistory.shanepark.coresecurity.security.service

import com.tistory.shanepark.coresecurity.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service("UserDetailsService")
class UserDetailsServiceImpl(
    val userRepository: UserRepository,
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val account = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("username not found")

        val roles = account.userRoles
            ?.stream()
            ?.map { r -> SimpleGrantedAuthority(r.roleName) }?.collect(Collectors.toList())

        return AccountContext(account, roles)

    }
}
