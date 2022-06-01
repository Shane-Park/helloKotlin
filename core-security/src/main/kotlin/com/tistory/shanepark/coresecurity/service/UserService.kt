package com.tistory.shanepark.coresecurity.service

import com.tistory.shanepark.coresecurity.domain.dto.AccountDto
import com.tistory.shanepark.coresecurity.domain.entity.Account

interface UserService {
    fun createUser(account: Account)

    fun modifyUser(accountDto: AccountDto)

    fun getUsers(): List<Account>?

    fun getUser(id: Long): AccountDto?

    fun deleteUser(idx: Long)
}
