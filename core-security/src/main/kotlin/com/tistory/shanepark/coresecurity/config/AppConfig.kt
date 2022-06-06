package com.tistory.shanepark.coresecurity.config

import com.tistory.shanepark.coresecurity.repository.AccessIpRepository
import com.tistory.shanepark.coresecurity.repository.ResourcesRepository
import com.tistory.shanepark.coresecurity.service.SecurityResourceService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun securityResourceService(
        resourcesRepository: ResourcesRepository,
        accessIpRepository: AccessIpRepository
    ): SecurityResourceService {
        return SecurityResourceService(resourcesRepository, accessIpRepository)
    }
}
