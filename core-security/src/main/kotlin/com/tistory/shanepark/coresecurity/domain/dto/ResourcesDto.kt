package com.tistory.shanepark.coresecurity.domain.dto

import com.tistory.shanepark.coresecurity.domain.entity.Role


data class ResourcesDto(
    private var id: String? = null,
    var resourceName: String? = null,
    var httpMethod: String? = null,
    var orderNum: Int = 0,
    var resourceType: String? = null,
    var roleName: String? = null,
    var roleSet: Set<Role>? = null,
) {}
