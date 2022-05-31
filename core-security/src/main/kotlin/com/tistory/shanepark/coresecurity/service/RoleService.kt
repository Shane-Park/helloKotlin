package com.tistory.shanepark.coresecurity.service

import com.tistory.shanepark.coresecurity.domain.entity.Role

interface RoleService {
    fun getRole(id: Long): Role?
    val roles: MutableList<Role?>
    fun createRole(role: Role)
    fun deleteRole(id: Long)
}
