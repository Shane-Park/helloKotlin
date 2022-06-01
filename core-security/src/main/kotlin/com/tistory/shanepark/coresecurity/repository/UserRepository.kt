package com.tistory.shanepark.coresecurity.repository

import com.tistory.shanepark.coresecurity.domain.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Account, Long> {

    fun findByUsername(username: String): Account?
    fun countByUsername(username: String?): Int

}
