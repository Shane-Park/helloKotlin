package com.tistory.shanepark.coresecurity.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Account(
    username: String?,
    password: String?,
    email: String?,
    age: Int?,
    role: String?,
) {
    constructor() : this(null, null, null, null, null)

    @Id
    @GeneratedValue
    var id: Long? = null
    var username: String? = username
    var password: String? = password
    var email: String? = email
    var age: Int? = age
    var role: String? = role

    companion object {
        fun fromDto(dto: AccountDto): Account {
            val account = Account()
            account.username = dto.username
            account.password = dto.password
            account.email = dto.email
            account.age = dto.age
            account.role = dto.role
            return account
        }
    }

}
