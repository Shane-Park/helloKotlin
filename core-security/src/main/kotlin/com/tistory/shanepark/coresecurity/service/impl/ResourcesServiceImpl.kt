package com.tistory.shanepark.coresecurity.service.impl

import com.tistory.shanepark.coresecurity.domain.entity.Resources
import com.tistory.shanepark.coresecurity.repository.ResourcesRepository
import com.tistory.shanepark.coresecurity.service.ResourcesService
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ResourcesServiceImpl(
    private val ResourcesRepository: ResourcesRepository,
) : ResourcesService {
    @Transactional
    override fun getResources(id: Long): Resources? {
        return ResourcesRepository.findById(id).orElse(Resources())
    }

    @Transactional
    override fun getResources(): List<Resources?>? {
        return ResourcesRepository.findAll(Sort.by(Sort.Order.asc("orderNum")))
    }

    @Transactional
    override fun createResources(resources: Resources) {
        ResourcesRepository.save(resources)
    }

    @Transactional
    override fun deleteResources(id: Long) {
        ResourcesRepository.deleteById(id)
    }
}
