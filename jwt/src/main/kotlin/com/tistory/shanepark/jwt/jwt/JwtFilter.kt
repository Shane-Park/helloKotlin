package com.tistory.shanepark.jwt.jwt

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtFilter(
    val tokenProvider: TokenProvider
) : GenericFilterBean() {

    val log = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    val authHeader = "Authorization"

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpServletRequest = request as HttpServletRequest
        val jwt = resolveToken(httpServletRequest)
        val requestURI = httpServletRequest.requestURI

        if (jwt != null && tokenProvider.validateToken(jwt)) {
            val authentication = tokenProvider.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.name, requestURI)
        } else {
            log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI)
        }
    }

    private fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader(authHeader)
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        return null
    }

}
