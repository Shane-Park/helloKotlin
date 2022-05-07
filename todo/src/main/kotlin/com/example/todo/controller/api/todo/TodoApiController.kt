package com.example.todo.controller.api.todo

import com.example.todo.model.http.TodoDto
import com.example.todo.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todo")
class TodoApiController(
    val todoService: TodoService,
) {

    // Create
    @PostMapping("")
    fun create(@RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.create(todoDto)
    }

    // Read
    @GetMapping(value = [""], params = ["index"])
    fun read(@RequestParam index: Int): TodoDto? {
        return todoService.read(index)
    }

    @GetMapping("")
    fun readAll(): MutableList<TodoDto> {
        return todoService.readAll()
    }

    // Update
    @PutMapping("") // create = 201, update = 200
    fun update(@RequestBody todoDto: TodoDto) {
        todoService.update(todoDto)
    }

    // Delete
    @DeleteMapping("/{index}")
    fun delete(@PathVariable("index") _index: Int): ResponseEntity<Any> {
        if (!todoService.delete(_index)) {
            return ResponseEntity.status(500).build()
        }
        return ResponseEntity.ok().build()
    }

}
