package com.example.jpatest.entity

import jakarta.persistence.*

@Entity
class Issue(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var title: String,
    var content: String,

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id")
    val milestone: Milestone,

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    val author: Member,

    )
