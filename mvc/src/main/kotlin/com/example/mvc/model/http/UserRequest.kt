package com.example.mvc.model.http

import com.example.mvc.annotation.StringFormatDateTime
import javax.validation.constraints.*

//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRequest(
    @field:NotEmpty
    @field:Size(min = 2, max = 8)
    var name: String? = null,
    @field:PositiveOrZero
    var age: Int? = null,
    @field:Email
    var email: String? = null,
    @field:NotBlank
    var address: String? = null,

//    @JsonProperty("phone_number")
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$")
    var phoneNumber: String? = null,

    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "Date format is invalid")
    var createdDate: String? = null,
)

