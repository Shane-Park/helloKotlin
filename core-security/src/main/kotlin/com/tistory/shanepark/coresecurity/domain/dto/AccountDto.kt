package com.tistory.shanepark.coresecurity.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class AccountDto(
    @JsonProperty("username") val username: String,
    @JsonProperty("password") var password: String,
    @JsonProperty("email") val email: String?,
    @JsonProperty("age") val age: Int?,
    @JsonProperty("role") var roles: List<String>?,
)
