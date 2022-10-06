package com.tistory.shanepark.jwt.util

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

class SecurityUtil {

    companion object {
        fun getCurrentUsername(): String? {
            val authentication = SecurityContextHolder.getContext().authentication ?: return null

            return if (authentication.principal is UserDetails) {
                val userDetails = authentication.principal as UserDetails
                userDetails.username
            } else {
                authentication.principal.toString()
            }
        }
    }
}
