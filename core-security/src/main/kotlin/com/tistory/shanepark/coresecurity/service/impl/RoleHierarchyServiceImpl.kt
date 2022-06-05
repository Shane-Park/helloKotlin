package com.tistory.shanepark.coresecurity.service.impl

import com.tistory.shanepark.coresecurity.domain.entity.RoleHierarchy
import com.tistory.shanepark.coresecurity.repository.RoleHierarchyRepository
import com.tistory.shanepark.coresecurity.service.RoleHierarchyService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RoleHierarchyServiceImpl(
    private val roleHierarchyRepository: RoleHierarchyRepository,
) : RoleHierarchyService {

    @Transactional
    override fun findAllHierarchy(): String {
        val rolesHierarchy = roleHierarchyRepository.findAll()
        val itr = rolesHierarchy.iterator()
        val concatenatedRoles = StringBuffer()
        while (itr.hasNext()) {
            val model: RoleHierarchy? = itr.next()
            if (model?.parentName != null) {
                concatenatedRoles.append(model.parentName!!.childName)
                concatenatedRoles.append(" > ")
                concatenatedRoles.append(model.childName)
                concatenatedRoles.append("\n")
            }
        }
        return concatenatedRoles.toString()
    }
}
