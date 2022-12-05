package com.example.jpatest.repository

import com.example.jpatest.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>
