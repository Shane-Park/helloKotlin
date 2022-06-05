package com.tistory.shanepark.coresecurity.domain.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "ROLE_HIERARCHY")
class RoleHierarchy(
    id: Long?,
    childName: String?,
    parentName: RoleHierarchy?,
) : Serializable {
    constructor() : this(null, null, null)

    @Id
    @GeneratedValue
    val id: Long? = id

    @Column(name = "child_name")
    val childName: String? = childName

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_name", referencedColumnName = "child_name")
    var parentName: RoleHierarchy? = parentName

    @OneToMany(mappedBy = "parentName", cascade = [CascadeType.ALL])
    val roleHierarchy: Set<RoleHierarchy> = HashSet()
}
