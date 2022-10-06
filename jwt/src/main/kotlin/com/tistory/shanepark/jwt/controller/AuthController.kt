package com.tistory.shanepark.jwt.controller

import com.tistory.shanepark.jwt.dto.LoginDto
import com.tistory.shanepark.jwt.dto.TokenDto
import com.tistory.shanepark.jwt.jwt.JwtFilter
import com.tistory.shanepark.jwt.jwt.TokenProvider
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class AuthController(
    private val tokenProvider: TokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder
) {
    private val log = org.slf4j.LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/authenticate")
    fun authenticate(@Valid @RequestBody loginDto: LoginDto): ResponseEntity<TokenDto> {
        val authenticationToken =
            UsernamePasswordAuthenticationToken(loginDto.username, loginDto.password)

        try {
            val authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken)

            SecurityContextHolder.getContext().authentication = authenticate

            val jwt = tokenProvider.createToken(authenticate)

            val httpHeaders = HttpHeaders()
            httpHeaders.add(JwtFilter.authHeader, "Bearer $jwt")
            return ResponseEntity(TokenDto(jwt), httpHeaders, HttpStatus.OK)
        } catch (e: Exception) {
            log.error("authenticate error : {}", e)
            throw e
        }
    }

}
