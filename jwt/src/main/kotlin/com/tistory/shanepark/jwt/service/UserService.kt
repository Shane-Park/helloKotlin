package com.tistory.shanepark.jwt.service

import com.tistory.shanepark.jwt.dto.UserDto
import com.tistory.shanepark.jwt.entity.Authority
import com.tistory.shanepark.jwt.entity.User
import com.tistory.shanepark.jwt.repository.UserRepository
import com.tistory.shanepark.jwt.util.SecurityUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun signUp(userDto: UserDto): User {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.username).isPresent) {
            throw RuntimeException("이미 존재하는 아이디입니다.")
        }
        val authority = Authority("ROLE_USER")
        val user = User(
            username = userDto.username,
            password = passwordEncoder.encode(userDto.password),
            nickname = userDto.nickname,
            authorities = mutableSetOf(authority),
            activated = true
        )
        return userRepository.save(user)
    }

    @Transactional(readOnly = true)
    fun getUserWithAuthorities(username: String): User? {
        return userRepository.findOneWithAuthoritiesByUsername(username).orElse(null)
    }

    @Transactional(readOnly = true)
    fun getUserWithAuthorities(): User? {
        return SecurityUtil.getCurrentUsername()
            ?.let { userRepository.findOneWithAuthoritiesByUsername(it) }
            ?.orElse(null)
    }

}
