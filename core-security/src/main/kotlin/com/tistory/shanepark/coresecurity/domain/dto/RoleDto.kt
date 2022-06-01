package com.tistory.shanepark.coresecurity.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RoleDto(
    @JsonProperty val id: String?,
    @JsonProperty val roleName: String?,
    @JsonProperty val roleDesc: String?,
) {
    constructor() : this("", "", "")
}
