package com.tistory.shanepark.coresecurity.domain.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "ACCESS_IP")
class AccessIp(
    ipAddress:String
) : Serializable {
    @Id
    @GeneratedValue
    @Column(name = "IP_ID", unique = true, nullable = false)
    private val id: Long? = null

    @Column(name = "IP_ADDRESS", nullable = false)
    private val ipAddress: String? = ipAddress
}
