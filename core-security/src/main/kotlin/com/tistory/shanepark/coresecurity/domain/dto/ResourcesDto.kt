package com.tistory.shanepark.coresecurity.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.tistory.shanepark.coresecurity.domain.entity.Resources
import com.tistory.shanepark.coresecurity.domain.entity.Role


data class ResourcesDto(
    @JsonProperty val id: String,
    @JsonProperty val resourceName: String?,
    @JsonProperty val httpMethod: String?,
    @JsonProperty val orderNum: Int?,
    @JsonProperty val resourceType: String?,
    @JsonProperty val roleName: String?,
    @JsonProperty var roleSet: Set<Role>?,
) {

    constructor() : this("", "", "", null, "", null, emptySet())

    companion object {
        fun fromResources(r: Resources): ResourcesDto {
            return ResourcesDto(r.id.toString(), r.resourceName, r.httpMethod, r.orderNum, r.resourceType, null, r.roleSet)
        }
    }

    fun toResource(): Resources {
        return Resources(id.toLong(), resourceName, null, httpMethod, resourceType, orderNum)
    }
}
