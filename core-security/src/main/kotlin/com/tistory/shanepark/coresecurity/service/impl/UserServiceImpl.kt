package com.tistory.shanepark.coresecurity.service.impl

import com.tistory.shanepark.coresecurity.domain.entity.Account
import com.tistory.shanepark.coresecurity.repository.UserRepository
import com.tistory.shanepark.coresecurity.service.UserService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Transactional
    override fun createUser(account: Account) {
        userRepository.save(account)
    }

}
