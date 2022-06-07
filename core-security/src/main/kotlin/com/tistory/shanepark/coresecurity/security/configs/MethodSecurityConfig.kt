package com.tistory.shanepark.coresecurity.security.configs

import org.springframework.context.annotation.Configuration
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource
import org.springframework.security.access.method.MethodSecurityMetadataSource
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class MethodSecurityConfig : GlobalMethodSecurityConfiguration() {

    override fun customMethodSecurityMetadataSource(): MethodSecurityMetadataSource {
        return MapBasedMethodSecurityMetadataSource()
    }
}
