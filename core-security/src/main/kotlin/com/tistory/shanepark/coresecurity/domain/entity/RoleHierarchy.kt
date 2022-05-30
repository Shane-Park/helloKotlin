package com.tistory.shanepark.coresecurity.domain.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "ROLE_HIERARCHY")
class RoleHierarchy(
    childName: String?,
) : Serializable {
    constructor() : this(null) {}

    @Id
    @GeneratedValue
    private val id: Long? = null

    @Column(name = "child_name")
    var childName: String? = childName

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_name", referencedColumnName = "child_name")
    var parentName: RoleHierarchy? = null

    @OneToMany(mappedBy = "parentName", cascade = [CascadeType.ALL])
    var roleHierarchy: Set<RoleHierarchy> = HashSet()
}
