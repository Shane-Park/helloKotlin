package com.tistory.shanepark.coresecurity.controller.admin

import com.tistory.shanepark.coresecurity.domain.dto.RoleDto
import com.tistory.shanepark.coresecurity.domain.entity.Role
import com.tistory.shanepark.coresecurity.service.RoleService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class RoleController(
    private val roleService: RoleService,
) {
    @GetMapping(value = ["/admin/roles"])
    fun getRoles(model: Model): String {
        val roles: List<Role>? = roleService.getRoles()
        model.addAttribute("roles", roles)
        return "admin/role/list"
    }

    @GetMapping(value = ["/admin/roles/register"])
    fun viewRoles(model: Model): String? {
        val role = RoleDto()
        model.addAttribute("role", role)
        return "admin/role/detail"
    }

    @PostMapping(value = ["/admin/roles"])
    fun createRole(roleDto: RoleDto?): String {
        val modelMapper = ModelMapper()
        val role: Role = modelMapper.map(roleDto, Role::class.java)
        roleService.createRole(role)
        return "redirect:/admin/roles"
    }

    @GetMapping(value = ["/admin/roles/{id}"])
    fun getRole(@PathVariable id: String?, model: Model): String {
        val role: Role? = roleService.getRole(java.lang.Long.valueOf(id))
        val modelMapper = ModelMapper()
        val roleDto: RoleDto = modelMapper.map(role, RoleDto::class.java)
        model.addAttribute("role", roleDto)
        return "admin/role/detail"
    }

    @GetMapping(value = ["/admin/roles/delete/{id}"])
    fun removeResources(@PathVariable id: String?, model: Model?): String {
        val role: Role? = roleService.getRole(java.lang.Long.valueOf(id))
        roleService.deleteRole(java.lang.Long.valueOf(id))
        return "redirect:/admin/resources"
    }
}
