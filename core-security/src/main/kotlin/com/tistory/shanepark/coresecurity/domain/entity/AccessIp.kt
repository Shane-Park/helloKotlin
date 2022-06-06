package com.tistory.shanepark.coresecurity.domain.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "ACCESS_IP")
class AccessIp(
    ipAddress: String,
) : Serializable {
    constructor() : this("")

    @Id
    @GeneratedValue
    @Column(name = "IP_ID", unique = true, nullable = false)
    val id: Long? = null

    @Column(name = "IP_ADDRESS", nullable = false)
    val ipAddress: String = ipAddress
}
