package com.tistory.shanepark.coresecurity.security.configs

import com.tistory.shanepark.coresecurity.security.factory.MethodResourcesFactoryBean
import com.tistory.shanepark.coresecurity.security.processor.ProtectPointcutPostProcessor
import com.tistory.shanepark.coresecurity.service.SecurityResourceService
import org.springframework.beans.factory.config.BeanPostProcessor
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
    fun mapBasedMethodSecurityMetadataSource(): MapBasedMethodSecurityMetadataSource {
        return MapBasedMethodSecurityMetadataSource(methodResourcesMapFactoryBean().getObject())
    }

    @Bean
    fun methodResourcesMapFactoryBean(): MethodResourcesFactoryBean {
        return MethodResourcesFactoryBean(
            securityResourceService = securityResourcesService, resourceType = "method")
    }

    @Bean
    fun pointcutResourcesMapFactoryBean(): MethodResourcesFactoryBean {
        return MethodResourcesFactoryBean(
            securityResourceService = securityResourcesService, resourceType = "pointcut")
    }

    @Bean
    fun protectPointcutPostProcessor(): ProtectPointcutPostProcessor {
        val protectPointcutPostProcessor = ProtectPointcutPostProcessor(mapBasedMethodSecurityMetadataSource())
        protectPointcutPostProcessor.setPointcutMap(pointcutResourcesMapFactoryBean().`object`)
        return protectPointcutPostProcessor
    }

    //    @Bean
//    fun protectPointcutPostProcessor(): BeanPostProcessor {
//        val clazz = Class.forName("org.springframework.security.config.method.ProtectPointcutPostProcessor")
//        val declaredConstructor = clazz.getDeclaredConstructor(MapBasedMethodSecurityMetadataSource::class.java)
//        declaredConstructor.isAccessible = true
//        val instance = declaredConstructor.newInstance(mapBasedMethodSecurityMetadataSource())
//        val setPointcutMap = instance.javaClass.getMethod("setPointcutMap", Map::class.java)
//        setPointcutMap.isAccessible = true
//        setPointcutMap.invoke(instance, pointcutResourcesMapFactoryBean().`object`)
//        return instance as BeanPostProcessor
//    }
}
