package com.tistory.shanepark.jwt.controller

import com.tistory.shanepark.jwt.dto.UserDto
import com.tistory.shanepark.jwt.entity.User
import com.tistory.shanepark.jwt.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody userDto: UserDto): ResponseEntity<User> {
        return ResponseEntity.ok(userService.signUp(userDto = userDto))
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun getMyUserInfo(): ResponseEntity<User> {
        return ResponseEntity.ok(userService.getUserWithAuthorities())
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun getUserInfo(@PathVariable username: String): ResponseEntity<User> {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username))
    }
}
