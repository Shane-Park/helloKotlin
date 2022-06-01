package com.tistory.shanepark.coresecurity.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.tistory.shanepark.coresecurity.domain.entity.Role

data class RoleDto(
    @JsonProperty val id: String?,
    @JsonProperty val roleName: String?,
    @JsonProperty val roleDesc: String?,
) {
    constructor() : this("", "", "")

    companion object {
        fun fromRole(r: Role): RoleDto {
            return RoleDto(r.id.toString(), r.roleName, r.roleDesc)
        }
    }

    fun toRole(): Role {
        return Role(roleName, roleDesc)
    }
}
