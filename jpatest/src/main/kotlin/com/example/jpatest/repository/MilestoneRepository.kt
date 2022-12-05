package com.example.jpatest.repository

import com.example.jpatest.entity.Milestone
import org.springframework.data.jpa.repository.JpaRepository

interface MilestoneRepository : JpaRepository<Milestone, Long>
