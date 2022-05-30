package com.tistory.shanepark.coresecurity.controller.admin

import io.security.corespringsecurity.domain.dto.AccountDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@Controller
class UserManagerController {
    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val roleService: RoleService? = null
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
        userService.modifyUser(accountDto)
        return "redirect:/admin/accounts"
    }

    @GetMapping(value = ["/admin/accounts/{id}"])
    fun getUser(@PathVariable(value = "id") id: Long?, model: Model): String {
        val accountDto: AccountDto = userService.getUser(id)
        val roleList: List<Role> = roleService.getRoles()
        model.addAttribute("account", accountDto)
        model.addAttribute("roleList", roleList)
        return "admin/user/detail"
    }

    @GetMapping(value = ["/admin/accounts/delete/{id}"])
    fun removeUser(@PathVariable(value = "id") id: Long?, model: Model?): String {
        userService.deleteUser(id)
        return "redirect:/admin/users"
    }
}
