package com.tistory.shanepark.coresecurity.security.filter

import org.springframework.security.access.intercept.InterceptorStatusToken
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.FilterConfig

class PermitAllFilter(
    permitAllResources: Array<String>,
) : FilterSecurityInterceptor() {

    companion object {
        private const val FILTER_APPLIED = "__spring_security_filterSecurityInterceptor_filterApplied"
    }

    private val observeOncePerRequest = true
    private val permitAllRequestMatchers: MutableList<RequestMatcher> = mutableListOf()

    init {
        for (resource in permitAllResources) {
            permitAllRequestMatchers.add(AntPathRequestMatcher(resource))
        }
    }


    override fun beforeInvocation(`object`: Any?): InterceptorStatusToken? {
        var permitAll = false
        val request = (`object` as FilterInvocation).request
        for (requestMatcher in permitAllRequestMatchers) {
            if (requestMatcher.matches(request)) {
                permitAll = true
                break
            }
        }
        if (permitAll)
            return null

        return super.beforeInvocation(`object`)
    }

    override fun init(arg0: FilterConfig?) {}
    override fun destroy() {}

    override fun invoke(filterInvocation: FilterInvocation) {
        if (isApplied(filterInvocation) && this.observeOncePerRequest) {
            // filter already applied to this request and user wants us to observe
            // once-per-request handling, so don't re-do security checking
            filterInvocation.chain.doFilter(filterInvocation.request, filterInvocation.response)
            return
        }
        // first time this request being called, so perform security checking
        if (filterInvocation.request != null && this.observeOncePerRequest) {
            filterInvocation.request.setAttribute(FILTER_APPLIED, java.lang.Boolean.TRUE)
        }
        val token = beforeInvocation(filterInvocation)
        try {
            filterInvocation.chain.doFilter(filterInvocation.request, filterInvocation.response)
        } finally {
            super.finallyInvocation(token)
        }
        super.afterInvocation(token, null)
    }

    private fun isApplied(filterInvocation: FilterInvocation): Boolean {
        return filterInvocation.request != null && (filterInvocation.request.getAttribute(FILTER_APPLIED) != null)
    }
}
