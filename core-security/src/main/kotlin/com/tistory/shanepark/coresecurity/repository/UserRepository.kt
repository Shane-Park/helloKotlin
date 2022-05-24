package com.tistory.shanepark.coresecurity.repository

import com.tistory.shanepark.coresecurity.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Account, Long> {

    fun findByUsername(username: String): Account?

}
