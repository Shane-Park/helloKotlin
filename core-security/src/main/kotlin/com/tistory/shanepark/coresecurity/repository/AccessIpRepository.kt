package com.tistory.shanepark.coresecurity.repository

import com.tistory.shanepark.coresecurity.domain.entity.AccessIp
import org.springframework.data.jpa.repository.JpaRepository

interface AccessIpRepository : JpaRepository<AccessIp?, Long?> {
    fun findByIpAddress(IpAddress: String?): AccessIp?
}
