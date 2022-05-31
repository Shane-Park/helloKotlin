package com.tistory.shanepark.coresecurity.service

import com.tistory.shanepark.coresecurity.domain.dto.AccountDto
import com.tistory.shanepark.coresecurity.domain.entity.Account

interface UserService {

    fun createUser(account: Account)
    fun getUsers(): List<Account>
    fun getUser(id: Long): AccountDto?
    fun deleteUser(id: Long)

}
