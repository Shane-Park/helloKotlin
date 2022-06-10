package com.tistory.shanepark.coresecurity.aopsecurity

import org.springframework.stereotype.Service

@Service
class AopPointcutService {

    fun pointcutSecured() {
        println("pointcutSecured")
    }

    fun notSecured() {
        println("notSecured")
    }

}
