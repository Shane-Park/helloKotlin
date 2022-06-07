package com.tistory.shanepark.coresecurity.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.tistory.shanepark.coresecurity.domain.entity.Account


data class AccountDto(
    @JsonProperty("id") val id: String?,
    @JsonProperty("username") val username: String?,
    @JsonProperty("password") var password: String?,
    @JsonProperty("email") val email: String?,
    @JsonProperty("age") val age: Int?,
    @JsonProperty("role") var roles: List<String>?,
) {
    companion object {
        fun fromAccount(account: Account): AccountDto {
            return AccountDto(
                account.id.toString(), account.username, account.password, account.email, account.age, null)
        }
    }

    fun toAccount(): Account {
        return Account(this.id?.toLong(), this.username, this.password, this.email, this.age, null)
    }
}
