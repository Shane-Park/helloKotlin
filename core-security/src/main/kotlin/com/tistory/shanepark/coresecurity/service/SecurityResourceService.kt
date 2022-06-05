package com.tistory.shanepark.coresecurity.service

import com.tistory.shanepark.coresecurity.repository.ResourcesRepository
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.access.SecurityConfig
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher

class SecurityResourceService(
    private val resourcesRepository: ResourcesRepository,
) {

    fun getResourceList(): LinkedHashMap<RequestMatcher, List<ConfigAttribute>> {
        val result: LinkedHashMap<RequestMatcher, List<ConfigAttribute>> = LinkedHashMap()
        val resourceList = resourcesRepository.findAllResources()
        resourceList?.forEach { resource ->
            val configAttributeList: MutableList<ConfigAttribute> = ArrayList()
            resource?.roleSet?.forEach { role ->
                configAttributeList.add(SecurityConfig(role.roleName))
            }
            result[AntPathRequestMatcher(resource?.resourceName)] = configAttributeList
        }
        return result
    }

}
