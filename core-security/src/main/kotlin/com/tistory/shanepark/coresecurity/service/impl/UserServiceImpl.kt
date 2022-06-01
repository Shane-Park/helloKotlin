package com.tistory.shanepark.coresecurity.service.impl

import com.tistory.shanepark.coresecurity.domain.dto.AccountDto
import com.tistory.shanepark.coresecurity.domain.entity.Account
import com.tistory.shanepark.coresecurity.domain.entity.Role
import com.tistory.shanepark.coresecurity.repository.RoleRepository
import com.tistory.shanepark.coresecurity.repository.UserRepository
import com.tistory.shanepark.coresecurity.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors


@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserService {
    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional
    override fun createUser(account: Account) {
        val role: Role? = roleRepository.findByRoleName("ROLE_USER")
        val roles: MutableSet<Role> = HashSet()
        if (role != null) {
            roles.add(role)
        }
        account.userRoles = roles
        userRepository.save(account)
    }

    @Transactional
    override fun modifyUser(accountDto: AccountDto) {
        val account = accountDto.toAccount()
        if (accountDto.roles != null) {
            val roles: MutableSet<Role> = HashSet()
            accountDto.roles!!.forEach { role ->
                val r: Role? = role?.let { roleRepository.findByRoleName(it) }
                if (r != null) {
                    roles.add(r)
                }
            }
            account.userRoles = roles
        }
        account.password = passwordEncoder.encode(accountDto.password)
        userRepository.save(account)
    }

    @Transactional
    override fun getUser(id: Long): AccountDto? {
        val account = userRepository.findById(id).orElse(Account())
        val accountDto = AccountDto.fromAccount(account)
        val roles: MutableList<String>? = account.userRoles
            ?.stream()
            ?.map { role -> role.roleName }
            ?.collect(Collectors.toList())
        accountDto.roles = roles
        return accountDto
    }

    @Transactional
    override fun getUsers(): List<Account>? {
        return userRepository.findAll()
    }

    override fun deleteUser(id: Long) {
        userRepository.deleteById(id!!)
    }

}
