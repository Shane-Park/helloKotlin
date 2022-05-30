package com.tistory.shanepark.coresecurity.service

import com.tistory.shanepark.coresecurity.domain.entity.Resources

interface ResourcesService {
    fun getResources(id: Long): Resources?
    val resources: List<Any?>?
    fun createResources(Resources: Resources)
    fun deleteResources(id: Long)
}
