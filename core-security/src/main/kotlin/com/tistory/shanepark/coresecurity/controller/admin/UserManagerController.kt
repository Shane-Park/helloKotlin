package com.tistory.shanepark.coresecurity.controller.admin

import com.tistory.shanepark.coresecurity.domain.dto.AccountDto
import com.tistory.shanepark.coresecurity.domain.entity.Account
import com.tistory.shanepark.coresecurity.domain.entity.Role
import com.tistory.shanepark.coresecurity.service.RoleService
import com.tistory.shanepark.coresecurity.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserManagerController(
    private val userService: UserService,
    private val roleService: RoleService,
) {
    @GetMapping(value = ["/admin/accounts"])
    @Throws(Exception::class)
    fun getUsers(model: Model): String {
        val accounts: List<Account> = userService.getUsers()
        model.addAttribute("accounts", accounts)
        return "admin/user/list"
    }

    @PostMapping(value = ["/admin/accounts"])
    @Throws(Exception::class)
    fun modifyUser(accountDto: AccountDto?): String {
//        userService.modifyUser(accountDto)
        return "redirect:/admin/accounts"
    }

    @GetMapping(value = ["/admin/accounts/{id}"])
    fun getUser(@PathVariable(value = "id") id: Long, model: Model): String {
        val accountDto: AccountDto ?= userService.getUser(id)
        val roleList: MutableList<Role?> = roleService.roles
        model.addAttribute("account", accountDto)
        model.addAttribute("roleList", roleList)
        return "admin/user/detail"
    }

    @GetMapping(value = ["/admin/accounts/delete/{id}"])
    fun removeUser(@PathVariable(value = "id") id: Long, model: Model?): String {
        userService.deleteUser(id)
        return "redirect:/admin/users"
    }
}
