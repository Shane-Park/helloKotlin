package com.tistory.shanepark.coresecurity.domain.entity

import com.tistory.shanepark.coresecurity.domain.dto.AccountDto
import javax.persistence.*

@Entity
class Account(
    username: String?,
    password: String?,
    email: String?,
    age: Int?,
    role: Set<Role>?,
) {
    constructor() : this(null, null, null, null, null)

    @Id
    @GeneratedValue
    var id: Long? = null
    var username: String? = username
    var password: String? = password
    var email: String? = email
    var age: Int? = age

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(name = "account_roles",
        joinColumns = [JoinColumn(name = "account_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")])
    var role: Set<Role>? = role

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
