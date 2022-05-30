package com.tistory.shanepark.coresecurity.controller.admin

import com.tistory.shanepark.coresecurity.domain.dto.ResourcesDto
import com.tistory.shanepark.coresecurity.domain.entity.Resources
import com.tistory.shanepark.coresecurity.domain.entity.Role
import com.tistory.shanepark.coresecurity.repository.RoleRepository
import com.tistory.shanepark.coresecurity.service.ResourcesService
import com.tistory.shanepark.coresecurity.service.RoleService
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
    private val methodSecurityService: MethodSecurityService,
    private val filterInvocationSecurityMetadataSource: UrlFilterInvocationSecurityMetadataSource,
) {

    @GetMapping(value = ["/admin/resources"])
    fun getResources(model: Model): String {
        val resources: List<Resources> = resourcesService.getResources()
        model.addAttribute("resources", resources)
        return "admin/resource/list"
    }

    @PostMapping(value = ["/admin/resources"])
    fun createResources(resourcesDto: ResourcesDto): String {
        val modelMapper = ModelMapper()
        val role: Role? = roleRepository.findByRoleName(resourcesDto.roleName)
        val roles: MutableSet<Role> = HashSet()
        role?.let { roles.add(it) }
        val resources: Resources = modelMapper.map(resourcesDto, Resources::class.java)
        resources.roleSet = roles
        resourcesService.createResources(resources)
        if ("url" == resourcesDto.resourceType) {
            filterInvocationSecurityMetadataSource.reload()
        } else {
            methodSecurityService.addMethodSecured(resourcesDto.resourceName, resourcesDto.roleName)
        }
        return "redirect:/admin/resources"
    }

    @GetMapping(value = ["/admin/resources/register"])
    fun viewRoles(model: Model): String {
        val roleList: List<Role> = roleService.getRoles()
        model.addAttribute("roleList", roleList)
        val resources = ResourcesDto()
        val roleSet: MutableSet<Role> = HashSet<Role>()
        roleSet.add(Role())
        resources.roleSet = roleSet
        model.addAttribute("resources", resources)
        return "admin/resource/detail"
    }

    @GetMapping(value = ["/admin/resources/{id}"])
    fun getResources(@PathVariable id: String?, model: Model): String {
        val roleList: List<Role> = roleService.getRoles()
        model.addAttribute("roleList", roleList)
        val resources: Resources = resourcesService.getResources(java.lang.Long.valueOf(id))
        val modelMapper = ModelMapper()
        val resourcesDto: ResourcesDto = modelMapper.map(resources, ResourcesDto::class.java)
        model.addAttribute("resources", resourcesDto)
        return "admin/resource/detail"
    }

    @GetMapping(value = ["/admin/resources/delete/{id}"])
    @Throws(Exception::class)
    fun removeResources(@PathVariable id: String?, model: Model?): String {
        val resources: Resources = resourcesService.getResources(java.lang.Long.valueOf(id))
        resourcesService.deleteResources(java.lang.Long.valueOf(id))
        if ("url" == resources.resourceType) {
            filterInvocationSecurityMetadataSource.reload()
        } else {
            methodSecurityService.removeMethodSecured(resources.resourceName)
        }
        return "redirect:/admin/resources"
    }
}
