package com.tistory.shanepark.coresecurity.service

import com.tistory.shanepark.coresecurity.domain.Account
import com.tistory.shanepark.coresecurity.repository.UserRepository

interface UserService {

    fun createUser(account: Account)

}
