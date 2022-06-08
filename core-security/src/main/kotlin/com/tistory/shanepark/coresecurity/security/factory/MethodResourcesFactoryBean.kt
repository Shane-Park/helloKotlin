package com.tistory.shanepark.coresecurity.security.factory

import com.tistory.shanepark.coresecurity.service.SecurityResourceService
import org.springframework.beans.factory.FactoryBean
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.web.util.matcher.RequestMatcher

class MethodResourcesFactoryBean(
    private val securityResourceService: SecurityResourceService,
    ) : FactoryBean<LinkedHashMap<String, List<ConfigAttribute>>> {

    private var resourceMap: LinkedHashMap<String, List<ConfigAttribute>>? = null

    override fun getObject(): LinkedHashMap<String, List<ConfigAttribute>> {
        if (resourceMap == null) {
            init();
        }
        return resourceMap!!

    }

    private fun init() {
        resourceMap = securityResourceService.getMethodResourceList()
    }

    override fun getObjectType(): Class<*>? {
        return LinkedHashMap::class.java
    }
}
