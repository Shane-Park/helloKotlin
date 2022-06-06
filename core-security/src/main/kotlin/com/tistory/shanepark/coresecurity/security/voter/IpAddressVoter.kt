package com.tistory.shanepark.coresecurity.security.voter

import com.tistory.shanepark.coresecurity.service.SecurityResourceService
import org.springframework.security.access.AccessDecisionVoter
import org.springframework.security.access.AccessDecisionVoter.ACCESS_ABSTAIN
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.WebAuthenticationDetails

class IpAddressVoter(
    private val securityResourceService: SecurityResourceService,
) : AccessDecisionVoter<Any> {

    override fun supports(attribute: ConfigAttribute?): Boolean {
        return true
    }

    override fun supports(clazz: Class<*>?): Boolean {
        return true
    }

    override fun vote(
        authentication: Authentication?,
        `object`: Any?,
        attributes: MutableCollection<ConfigAttribute>?,
    ): Int {
        val details: WebAuthenticationDetails = authentication?.details as WebAuthenticationDetails
        val remoteAddress = details.remoteAddress
        val accessIpList = securityResourceService.getAccessIpList()
        if (accessIpList?.contains(remoteAddress) == true) {
            return ACCESS_ABSTAIN
        }

        throw org.springframework.security.access.AccessDeniedException("Invalid IpAddress")
    }
}
