package com.example.jpatest.repository

import com.example.jpatest.entity.Issue
import org.springframework.data.jpa.repository.JpaRepository

interface IssueRepository : JpaRepository<Issue, Long>
