package com.example.todo.model.http

import com.example.todo.annotation.StringFormatDateTime
import com.example.todo.database.Todo
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotBlank

data class TodoDto(
    @field:ApiModelProperty(
        value = "DB index",
        example = "1",
        required = false
    )
    var index: Int? = null,

    @field:ApiModelProperty(
        value = "Todo title",
        example = "todo management",
        required = true
    )
    @field:NotBlank
    var title: String? = null,

    @field:ApiModelProperty(
        value = "Todo Description",
        example = "meet at Starbucks at 13:00",
        required = false
    )
    var description: String? = null,

    @field:ApiModelProperty(
        value = "Schedule time",
        example = "2022-05-08 00:01:02",
        required = true
    )
    @field:StringFormatDateTime
    // yyyy-MM-dd HH:mm:ss
    var schedule: String? = null,

    @field:ApiModelProperty(
        value = "Created Date",
        required = false
    )
    var createdAt: LocalDateTime? = null,
    @field:ApiModelProperty(
        value = "Updated Date",
        required = false
    )
    var updatedAt: LocalDateTime? = null,

    ) {

    @AssertTrue(message = "Dateformat should be yyyy-MM-dd HH:mm:ss")
    fun validSchedule(): Boolean {
        return try {
            LocalDateTime.parse(schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        } catch (e: java.lang.Exception) {
            false
        }
    }

}

fun TodoDto.of(todo: Todo): TodoDto {
    return TodoDto().apply {
        this.index = todo.index
        this.title = todo.title
        this.description = todo.description
        this.schedule = todo.schedule?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))
        this.createdAt = todo.createdAt
        this.updatedAt = todo.updatedAt
    }
}
