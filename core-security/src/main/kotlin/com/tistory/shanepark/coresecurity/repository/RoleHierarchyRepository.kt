package com.tistory.shanepark.coresecurity.repository

import com.tistory.shanepark.coresecurity.domain.entity.RoleHierarchy
import org.springframework.data.jpa.repository.JpaRepository

interface RoleHierarchyRepository : JpaRepository<RoleHierarchy?, Long?> {
    fun findByChildName(roleName: String?): RoleHierarchy?
}
