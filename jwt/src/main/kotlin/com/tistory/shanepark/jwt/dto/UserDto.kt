package com.tistory.shanepark.jwt.dto

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class UserDto(
    @field:NotNull
    @field:Size(min = 3, max = 50)
    val username: String,

    @field:NotNull
    @field:Size(min = 3, max = 100)
    val password: String,

    @field:NotNull
    @field:Size(min = 3, max = 50)
    val nickname: String,
) {

}
