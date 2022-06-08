package com.tistory.shanepark.coresecurity.security.configs

import com.tistory.shanepark.coresecurity.security.factory.MethodResourcesFactoryBean
import com.tistory.shanepark.coresecurity.service.SecurityResourceService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource
import org.springframework.security.access.method.MethodSecurityMetadataSource
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class MethodSecurityConfig(
    private val securityResourcesService: SecurityResourceService,
) : GlobalMethodSecurityConfiguration() {

    override fun customMethodSecurityMetadataSource(): MethodSecurityMetadataSource {
        return mapBasedMethodSecurityMetadataSource()
    }

    @Bean
    fun mapBasedMethodSecurityMetadataSource(): MethodSecurityMetadataSource {
        return MapBasedMethodSecurityMetadataSource(methodResourcesMapFactoryBean().getObject())
    }

    @Bean
    fun methodResourcesMapFactoryBean(): MethodResourcesFactoryBean {
        return MethodResourcesFactoryBean(securityResourcesService)
    }
}
