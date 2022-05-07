package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDataBase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoRepositoryImpl(val todoDateBase: TodoDataBase) : TodoRepository {

    override fun save(todo: Todo): Todo? {

        return todo.index?.let { index ->
            // if index already exist, update
            findOne(index)?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updatedAt = LocalDateTime.now()
            }
        } ?: kotlin.run {
            // else insert
            todo.apply {
                this.index = ++todoDateBase.index
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }.run {
                todoDateBase.todoList.add(todo)
                this
            }
        }

    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {
        return try {
            todoList.forEach {
                save(it)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun delete(index: Int): Boolean {
        val todo = findOne(index)

        return todo?.let {
            todoDateBase.todoList.remove(it)
        } ?: kotlin.run {
            false
        }
    }

    override fun findOne(index: Int): Todo? {
        return todoDateBase.todoList.first { it.index == index }
    }

    override fun findAll(): MutableList<Todo> {
        return todoDateBase.todoList
    }
}
