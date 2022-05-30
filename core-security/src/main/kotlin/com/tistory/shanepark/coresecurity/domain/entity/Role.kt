package com.tistory.shanepark.coresecurity.domain.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "ROLE")
class Role(
    roleName : String?,
    roleDesc : String?,

) : Serializable {
    constructor() : this(null, null)

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    var id: Long? = null

    @Column(name = "role_name")
    var roleName: String? = roleName

    @Column(name = "role_desc")
    var roleDesc: String? = roleDesc

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleSet")
    @OrderBy("ordernum desc")
    var resourcesSet: Set<Resources> = LinkedHashSet()

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
    var accounts: Set<Account> = HashSet()
}
