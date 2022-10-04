package com.tistory.shanepark.jwt.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "user_id")
    var userId: Long? = null,
    var username: String? = null,
    @JsonIgnore
    var password: String? = null,
    var nickname: String? = null,
    @JsonIgnore
    var activated: Boolean? = null,

    @ManyToMany
    @JoinTable(
        name = "user_authority",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "authority_name", referencedColumnName = "authority_name")]
    )
    var authorities: MutableSet<Authority> = mutableSetOf()
) {
}
