package com.tistory.shanepark.coresecurity.security.listener

import com.tistory.shanepark.coresecurity.domain.entity.Account
import com.tistory.shanepark.coresecurity.domain.entity.Resources
import com.tistory.shanepark.coresecurity.domain.entity.Role
import com.tistory.shanepark.coresecurity.repository.ResourcesRepository
import com.tistory.shanepark.coresecurity.repository.RoleRepository
import com.tistory.shanepark.coresecurity.repository.UserRepository
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
    private val passwordEncoder: PasswordEncoder,
) : ApplicationListener<ContextRefreshedEvent?> {
    private var alreadySetup = false

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (alreadySetup) {
            return
        }
        setupSecurityResources()
        alreadySetup = true
    }

    private fun setupSecurityResources() {
        val roles: MutableSet<Role> = HashSet()
        val adminRole: Role = createRoleIfNotFound("ROLE_ADMIN", "관리자")
        roles.add(adminRole)
        createResourceIfNotFound("/admin/**", "", roles, "url")
        val account: Account = createUserIfNotFound("admin", "pass", "admin@gmail.com", 10, roles)
    }

    @Transactional
    fun createRoleIfNotFound(roleName: String, roleDesc: String?): Role {
        var role: Role? = roleRepository.findByRoleName(roleName)
        if (role == null) {
            role = Role(roleName, roleDesc)
        }
        return roleRepository.save(role)
    }

    @Transactional
    fun createUserIfNotFound(
        userName: String,
        password: String?,
        email: String?,
        age: Int,
        roleSet: Set<Role>?,
    ): Account {
        var account: Account? = userRepository.findByUsername(userName)
        if (account == null) {
            account = Account(null, userName, passwordEncoder.encode(password), email, age, roleSet)
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
            resources = Resources(null, resourceName, roleSet, httpMethod, resourceType, count.incrementAndGet())
        }
        return resourcesRepository.save(resources)
    }

    companion object {
        private val count: AtomicInteger = AtomicInteger(0)
    }
}
