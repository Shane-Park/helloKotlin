package com.tistory.shanepark.coresecurity.security.factory

import com.tistory.shanepark.coresecurity.service.SecurityResourceService
import org.springframework.beans.factory.FactoryBean
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.web.util.matcher.RequestMatcher

class UrlResourcesMapFactoryBean(
    private val securityResourceService: SecurityResourceService,
) : FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {

    private var resourceMap: LinkedHashMap<RequestMatcher, List<ConfigAttribute>>? = null

    override fun getObject(): LinkedHashMap<RequestMatcher, List<ConfigAttribute>> {
        if (resourceMap == null) {
            init();
        }
        return resourceMap!!

    }

    private fun init() {
        resourceMap = securityResourceService.getResourceList()
    }

    override fun getObjectType(): Class<*>? {
        return LinkedHashMap::class.java
    }
}
