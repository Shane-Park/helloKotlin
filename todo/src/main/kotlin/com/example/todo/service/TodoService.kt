package com.example.todo.service

import com.example.todo.database.Todo
import com.example.todo.database.of
import com.example.todo.model.http.TodoDto
import com.example.todo.model.http.of
import com.example.todo.repository.TodoRepository
import org.springframework.stereotype.Service

/**
 * model mapper or
 * kotlin reflection are recommended to avoid human error
 */
@Service
class TodoService(val todoRepository: TodoRepository) {

    // Create
    fun create(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().of(todoDto)
        }.let {
            todoRepository.save(it)
        }?.let {
            TodoDto().of(it)
        }
    }

    // Read
    fun read(index: Int): TodoDto? {
        return todoRepository.findOne(index)?.let {
            TodoDto().of(it)
        }
    }

    fun readAll(): MutableList<TodoDto> {
        return todoRepository.findAll().map {
            TodoDto().of(it)
        }.toMutableList()
    }

    // Update
    fun update(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().of(it)
        }.let {
            todoRepository.save(it)
        }?.let {
            TodoDto().of(it)
        }
    }

    // Delete
    fun delete(index: Int): Boolean {
        return todoRepository.delete(index)
    }

}
