package com.tistory.shanepark.coresecurity.service

import com.tistory.shanepark.coresecurity.domain.entity.Account

interface UserService {

    fun createUser(account: Account)

}
