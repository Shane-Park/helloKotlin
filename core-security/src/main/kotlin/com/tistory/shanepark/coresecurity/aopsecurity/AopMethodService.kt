package com.tistory.shanepark.coresecurity.aopsecurity

import org.springframework.stereotype.Service

@Service
class AopMethodService {

    fun methodSecured() {
        println("MethodSecured")
    }

}
