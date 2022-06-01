package com.tistory.shanepark.coresecurity.service

import com.tistory.shanepark.coresecurity.domain.entity.Role

interface RoleService {

    fun getRole(id: Long): Role?

    fun getRoles(): List<Role>?

    fun createRole(role: Role)

    fun deleteRole(id: Long)
}
