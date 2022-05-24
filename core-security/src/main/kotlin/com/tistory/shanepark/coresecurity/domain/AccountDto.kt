package com.tistory.shanepark.coresecurity.domain

data class AccountDto(
    val username: String,
    val password: String,
    val email: String,
    val age: Int,
    val role: String,
)
