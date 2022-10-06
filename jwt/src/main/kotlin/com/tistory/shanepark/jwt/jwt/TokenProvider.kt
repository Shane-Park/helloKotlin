package com.tistory.shanepark.jwt.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class TokenProvider(
    @Value("\${jwt.secret}")
    private val secret: String,
    @Value("\${jwt.token-validity-in-seconds}")
    val tokenValidityInMilliSeconds: Long,
) : InitializingBean {

    val log = org.slf4j.LoggerFactory.getLogger(this.javaClass)

    private var key: Key? = null
    private final val authoritiesKey = "auth"

    override fun afterPropertiesSet() {
        val keyBytes = Decoders.BASE64.decode(secret)
        this.key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun createToken(authentication: Authentication): String {
        val authorities = authentication.authorities.stream()
            .map { it.authority }
            .collect(java.util.stream.Collectors.joining(","))
        val validity = Date(Date().time + tokenValidityInMilliSeconds)

        return Jwts.builder()
            .setSubject(authentication.name)
            .claim(authoritiesKey, authorities)
            .signWith(key, io.jsonwebtoken.SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body

        val authorities = claims[authoritiesKey].toString().split(",").map { SimpleGrantedAuthority(it) }

        val principal = User(claims.subject, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
            return true
        } catch (ex: Exception) {
            when (ex) {
                is MalformedJwtException -> log.info("잘못된 JWT 서명입니다.")
                is io.jsonwebtoken.security.SecurityException -> log.info("잘못된 JWT 서명입니다.")
                is ExpiredJwtException -> log.info("만료된 JWT 토큰입니다.")
                is UnsupportedJwtException -> log.info("지원되지 않는 JWT 토큰입니다.")
                is IllegalArgumentException -> log.info("JWT 토큰이 잘못되었습니다.")
                else -> log.info("JWT 토큰이 잘못되었습니다.")
            }
            return false
        }
    }

}
