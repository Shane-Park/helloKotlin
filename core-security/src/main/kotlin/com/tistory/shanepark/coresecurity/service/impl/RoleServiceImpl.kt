package com.tistory.shanepark.coresecurity.service.impl

import com.tistory.shanepark.coresecurity.domain.entity.Role
import com.tistory.shanepark.coresecurity.repository.RoleRepository
import com.tistory.shanepark.coresecurity.service.RoleService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {

    @Transactional
    override fun getRole(id: Long): Role? {
        return roleRepository.findById(id).orElse(Role())
    }

    @get:Transactional
    override val roles: MutableList<Role?>
        get() = roleRepository.findAll()

    @Transactional
    override fun createRole(role: Role) {
        roleRepository.save(role)
    }

    @Transactional
    override fun deleteRole(id: Long) {
        roleRepository.deleteById(id)
    }
}
