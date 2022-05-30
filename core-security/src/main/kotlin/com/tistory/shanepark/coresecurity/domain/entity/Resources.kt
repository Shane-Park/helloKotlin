package com.tistory.shanepark.coresecurity.domain.entity

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "RESOURCES")
@EntityListeners(value = [AuditingEntityListener::class])
class Resources(
    resourceName: String?,
    roleSet: Set<Role>?,
    httpMethod: String?,
    resourceType: String?,
    orderNum: Int?,
) : Serializable {
    constructor() : this(null, null, null, null, null)

    @Id
    @GeneratedValue
    @Column(name = "resource_id")
    private var id: Long? = null

    @Column(name = "resource_name")
    var resourceName: String? = resourceName

    @Column(name = "http_method")
    var httpMethod: String? = httpMethod

    @Column(name = "order_num")
    var orderNum = orderNum

    @Column(name = "resource_type")
    var resourceType: String? = resourceType

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_resources",
        joinColumns = [JoinColumn(name = "resource_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")])
    var roleSet: Set<Role> ?= roleSet
}
