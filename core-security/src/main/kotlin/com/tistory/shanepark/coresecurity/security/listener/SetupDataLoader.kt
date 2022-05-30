package com.tistory.shanepark.coresecurity.security.listener

import com.tistory.shanepark.coresecurity.domain.entity.*
import com.tistory.shanepark.coresecurity.repository.*
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.atomic.AtomicInteger

@Component
class SetupDataLoader(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val resourcesRepository: ResourcesRepository,
    private val roleHierarchyRepository: RoleHierarchyRepository,
    private val passwordEncoder: PasswordEncoder,
    private val accessIpRepository: AccessIpRepository,
) : ApplicationListener<ContextRefreshedEvent?> {
    private var alreadySetup = false

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (alreadySetup) {
            return
        }
        setupSecurityResources()
        setupAccessIpData()
        alreadySetup = true
    }

    private fun setupSecurityResources() {
        val roles: MutableSet<Role> = HashSet<Role>()
        val adminRole: Role = createRoleIfNotFound("ROLE_ADMIN", "관리자")
        roles.add(adminRole)
        createResourceIfNotFound("/admin/**", "", roles, "url")
        createResourceIfNotFound("execution(public * io.security.corespringsecurity.aopsecurity.*Service.pointcut*(..))",
            "",
            roles,
            "pointcut")
        createUserIfNotFound("admin", "admin@admin.com", "pass", roles)
        val managerRole: Role = createRoleIfNotFound("ROLE_MANAGER", "매니저권한")
        val userRole: Role = createRoleIfNotFound("ROLE_USER", "사용자권한")
        createRoleHierarchyIfNotFound(managerRole, adminRole)
        createRoleHierarchyIfNotFound(userRole, managerRole)
    }

    @Transactional
    fun createRoleIfNotFound(roleName: String?, roleDesc: String?): Role {
        var role: Role? = roleRepository.findByRoleName(roleName)
        if (role == null) {
            role = Role(roleName, roleDesc)
        }
        return roleRepository.save(role)
    }

    @Transactional
    fun createUserIfNotFound(userName: String, email: String?, password: String?, roleSet: Set<Role>?): Account {
        var account: Account? = userRepository.findByUsername(userName)
        if (account == null) {
            account = Account(userName, passwordEncoder.encode(password), email, null, roleSet)
        }
        return userRepository.save(account)
    }

    @Transactional
    fun createResourceIfNotFound(
        resourceName: String?,
        httpMethod: String?,
        roleSet: Set<Role>?,
        resourceType: String?,
    ): Resources {
        var resources: Resources? = resourcesRepository.findByResourceNameAndHttpMethod(resourceName, httpMethod)
        if (resources == null) {
            resources = Resources(resourceName, roleSet, httpMethod, resourceType, count.incrementAndGet())
        }
        return resourcesRepository.save(resources)
    }

    @Transactional
    fun createRoleHierarchyIfNotFound(childRole: Role, parentRole: Role) {
        var roleHierarchy: RoleHierarchy? = roleHierarchyRepository.findByChildName(parentRole.roleName)
        if (roleHierarchy == null) {
            roleHierarchy = RoleHierarchy(parentRole.roleName)
        }
        val parentRoleHierarchy: RoleHierarchy = roleHierarchyRepository.save(roleHierarchy)
        roleHierarchy = roleHierarchyRepository.findByChildName(childRole.roleName)
        if (roleHierarchy == null) {
            roleHierarchy = RoleHierarchy(childRole.roleName)
        }
        val childRoleHierarchy: RoleHierarchy = roleHierarchyRepository.save(roleHierarchy)
        childRoleHierarchy.parentName = parentRoleHierarchy
    }

    private fun setupAccessIpData() {
        val byIpAddress: AccessIp? = accessIpRepository.findByIpAddress("0:0:0:0:0:0:0:1")
        if (byIpAddress == null) {
            val accessIp: AccessIp = AccessIp("0:0:0:0:0:0:0:1")
            accessIpRepository.save(accessIp)
        }
    }

    companion object {
        private val count: AtomicInteger = AtomicInteger(0)
    }
}
