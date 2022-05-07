package com.example.todo.controller.api.todo

import com.example.todo.model.http.TodoDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todo")
class TodoApiController {

    // Create
    @PostMapping("")
    fun create(@RequestBody todoDto: TodoDto) {

    }

    // Read
    @GetMapping("")
    fun read(@RequestParam(required = false) index: Int) {

    }

    // Update
    @PutMapping("")
    fun update(@RequestBody todoDto: TodoDto) {

    }

    // Delete
    @DeleteMapping("/{index}")
    fun delete(@PathVariable("index") _index: String) {

    }

}
