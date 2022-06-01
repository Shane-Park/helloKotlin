package com.tistory.shanepark.coresecurity.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.tistory.shanepark.coresecurity.domain.entity.Role


data class ResourcesDto(
    @JsonProperty val id: String,
    @JsonProperty val resourceName: String?,
    @JsonProperty val httpMethod: String?,
    @JsonProperty val orderNum: Int?,
    @JsonProperty val resourceType: String?,
    @JsonProperty val roleName: String,
    @JsonProperty var roleSet: Set<Role>?,

    ) {
    constructor() : this("", "", "", null, "", "", null)
}
