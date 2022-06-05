package com.tistory.shanepark.coresecurity.security.init

import com.tistory.shanepark.coresecurity.service.RoleHierarchyService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.stereotype.Component

@Component
class SecurityInitializer(
    val roleHierarchyService: RoleHierarchyService,
    val roleHierarchy: RoleHierarchyImpl,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val allHierarchy = roleHierarchyService.findAllHierarchy()
        roleHierarchy.setHierarchy(allHierarchy)
    }
}
