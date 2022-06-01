package com.tistory.shanepark.coresecurity.domain.entity

import com.tistory.shanepark.coresecurity.domain.dto.AccountDto
import javax.persistence.*

@Entity
class Account(
    username: String,
    password: String,
    email: String?,
    age: Int?,
    roles: Set<Role>?,
) {
    constructor() : this("", "", null, null, null)

    @Id
    @GeneratedValue
    var id: Long? = null
    var username: String = username
    var password: String = password
    var email: String? = email
    var age: Int? = age

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var userRoles: Set<Role>? = roles

    companion object {
        fun fromDto(dto: AccountDto): Account {
            val account = Account()
            account.username = dto.username
            account.password = dto.password
            account.email = dto.email
            account.age = dto.age
//            account.roles = dto.roles
            return account
        }
    }

}
