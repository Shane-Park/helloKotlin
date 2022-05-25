package com.tistory.shanepark.coresecurity.domain

import com.fasterxml.jackson.annotation.JsonProperty


data class AccountDto(
    @JsonProperty("username") val username: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("email") val email: String?,
    @JsonProperty("age") val age: Int?,
    @JsonProperty("role") val role: String?,
)
