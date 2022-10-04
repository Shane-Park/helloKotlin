package com.tistory.shanepark.jwt.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Authority(
    @Id
    @Column(name = "authority_name")
    var authorityName: String? = null
)
