package com.tistory.shanepark.coresecurity.service.impl

import com.tistory.shanepark.coresecurity.domain.dto.AccountDto
import com.tistory.shanepark.coresecurity.domain.entity.Account
import com.tistory.shanepark.coresecurity.repository.UserRepository
import com.tistory.shanepark.coresecurity.service.UserService
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import javax.transaction.Transactional


@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Transactional
    override fun createUser(account: Account) {
        userRepository.save(account)
    }

    @Transactional
    override fun getUser(id: Long): AccountDto? {
        val account = userRepository.findById(id).orElse(Account())
        val roles = account.userRoles?.stream()
            ?.map { role -> role.roleName }
            ?.collect(Collectors.toSet())
        return AccountDto(account.username, account.password, account.email, account.age, roles)
    }

    @Transactional
    override fun getUsers(): List<Account> {
        return userRepository.findAll()
    }

    override fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

}
