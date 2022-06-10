package com.tistory.shanepark.coresecurity.security.factory

import com.tistory.shanepark.coresecurity.service.SecurityResourceService
import org.springframework.beans.factory.FactoryBean
import org.springframework.security.access.ConfigAttribute

class MethodResourcesFactoryBean(
    private val securityResourceService: SecurityResourceService,
    private val resourceType: String,
) : FactoryBean<Map<String, List<ConfigAttribute>>> {

    private var resourceMap: LinkedHashMap<String, List<ConfigAttribute>>? = null

    override fun getObject(): Map<String, List<ConfigAttribute>> {
        if (resourceMap == null) {
            init();
        }
        return resourceMap!!

    }

    private fun init() {
        if (resourceType == "method") {
            resourceMap = securityResourceService.getMethodResourceList()
        } else if (resourceType == "pointcut") {
            resourceMap = securityResourceService.getPointcutMethodResourceList()
        }
    }

    override fun getObjectType(): Class<*>? {
        return LinkedHashMap::class.java
    }
}
