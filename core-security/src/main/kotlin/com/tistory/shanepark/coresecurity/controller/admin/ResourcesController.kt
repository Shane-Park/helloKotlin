package com.tistory.shanepark.coresecurity.controller.admin

import com.tistory.shanepark.coresecurity.domain.dto.ResourcesDto
import com.tistory.shanepark.coresecurity.domain.entity.Resources
import com.tistory.shanepark.coresecurity.domain.entity.Role
import com.tistory.shanepark.coresecurity.repository.RoleRepository
import com.tistory.shanepark.coresecurity.security.metadatasource.UrlFilterInvocationSecurityMetadataSource
import com.tistory.shanepark.coresecurity.service.ResourcesService
import com.tistory.shanepark.coresecurity.service.RoleService
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ResourcesController(
    private val resourcesService: ResourcesService,
    private val roleRepository: RoleRepository,
    private val roleService: RoleService,
    private val filterInvocationSecurityMetadataSource: FilterInvocationSecurityMetadataSource,
) {

    @GetMapping(value = ["/admin/resources"])
    fun getResources(model: Model): String {
        val resources: List<Resources?>? = resourcesService.getResources()
        model.addAttribute("resources", resources)
        return "admin/resource/list"
    }

    @PostMapping(value = ["/admin/resources"])
    fun createResources(resourcesDto: ResourcesDto): String {
        val role: Role? = roleRepository.findByRoleName(resourcesDto.roleName!!)
        val roles: MutableSet<Role> = HashSet()
        role?.let { roles.add(it) }
        val resources: Resources = resourcesDto.toResource()
        resources.roleSet = roles
        resourcesService.createResources(resources)

        reloadRequestMap()

        return "redirect:/admin/resources"
    }

    @GetMapping(value = ["/admin/resources/register"])
    fun viewRoles(model: Model): String {
        val roleList: List<Role>? = roleService.getRoles()
        model.addAttribute("roleList", roleList)
        val resources = ResourcesDto()
        val roleSet: MutableSet<Role> = HashSet()
        roleSet.add(Role())
        resources.roleSet = roleSet
        model.addAttribute("resources", resources)
        return "admin/resource/detail"
    }

    @GetMapping(value = ["/admin/resources/{id}"])
    fun getResources(@PathVariable id: String?, model: Model): String {
        val roleList: List<Role>? = roleService.getRoles()
        model.addAttribute("roleList", roleList)
        val resources: Resources? = resourcesService.getResources(java.lang.Long.valueOf(id))
        val resourcesDto: ResourcesDto? = resources?.let { ResourcesDto.fromResources(it) }
        model.addAttribute("resources", resourcesDto)
        return "admin/resource/detail"
    }

    @GetMapping(value = ["/admin/resources/delete/{id}"])
    @Throws(Exception::class)
    fun removeResources(@PathVariable id: String?, model: Model?): String {
        val resources: Resources? = resourcesService.getResources(java.lang.Long.valueOf(id))
        resourcesService.deleteResources(java.lang.Long.valueOf(id))
        reloadRequestMap()

        return "redirect:/admin/resources"
    }

    private fun reloadRequestMap() {
        (filterInvocationSecurityMetadataSource as UrlFilterInvocationSecurityMetadataSource).reload()
    }
}
