package com.tistory.shanepark.coresecurity.repository

import com.tistory.shanepark.coresecurity.domain.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByRoleName(name: String): Role?
    override fun delete(role: Role)
}
