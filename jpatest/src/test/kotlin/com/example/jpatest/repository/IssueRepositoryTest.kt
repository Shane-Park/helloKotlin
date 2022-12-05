package com.example.jpatest.repository

import com.example.jpatest.entity.Issue
import com.example.jpatest.entity.Member
import com.example.jpatest.entity.Milestone
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IssueRepositoryTest {

    @Autowired
    lateinit var issueRepository: IssueRepository

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var milestoneRepository: MilestoneRepository

    @BeforeAll
    fun setup() {
        val member = memberRepository.save(Member(name = "user"))
        val milestone = milestoneRepository.save(Milestone(title = "milestone"))
        issueRepository.save(Issue(title = "issue", content = "content", author = member, milestone = milestone))
    }

    @Test
    fun `test how many queries called`() {
        issueRepository.findById(1L).get()
    }

}
