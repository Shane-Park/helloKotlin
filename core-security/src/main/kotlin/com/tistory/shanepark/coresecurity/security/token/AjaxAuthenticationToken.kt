package com.tistory.shanepark.coresecurity.security.token

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.SpringSecurityCoreVersion
import org.springframework.util.Assert

class AjaxAuthenticationToken(
    private val principal: Any?,
    private var credentials: Any?,
    authorities: Collection<GrantedAuthority?>?,
) : AbstractAuthenticationToken(authorities) {

    init {
        super.setAuthenticated(true) // must use super, as we override
    }

    private val serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID

    constructor(principal: Any?, credentials: Any?) : this(principal, credentials, null) {
        isAuthenticated = false
    }

    fun unauthenticated(principal: Any?, credentials: Any?): UsernamePasswordAuthenticationToken? {
        return UsernamePasswordAuthenticationToken(principal, credentials)
    }

    fun authenticated(
        principal: Any?, credentials: Any?,
        authorities: Collection<GrantedAuthority?>?,
    ): UsernamePasswordAuthenticationToken? {
        return UsernamePasswordAuthenticationToken(principal, credentials, authorities)
    }

    override fun getCredentials(): Any? {
        return credentials
    }

    override fun getPrincipal(): Any? {
        return principal
    }

    @Throws(IllegalArgumentException::class)
    override fun setAuthenticated(isAuthenticated: Boolean) {
        Assert.isTrue(!isAuthenticated,
            "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead")
        super.setAuthenticated(false)
    }

    override fun eraseCredentials() {
        super.eraseCredentials()
        credentials = null
    }
}
