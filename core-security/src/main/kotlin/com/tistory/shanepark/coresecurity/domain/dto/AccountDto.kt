package com.tistory.shanepark.coresecurity.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.tistory.shanepark.coresecurity.domain.entity.Role


data class AccountDto(
    @JsonProperty("username") val username: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("email") val email: String?,
    @JsonProperty("age") val age: Int?,
    @JsonProperty("role") val role: Set<Role>?,
)
