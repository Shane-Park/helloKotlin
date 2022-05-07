package com.example.todo.repository

import com.example.todo.database.Todo
import com.example.todo.database.TodoDataBase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [TodoRepositoryImpl::class, TodoDataBase::class])
internal class TodoRepositoryTest {

    @Autowired
    lateinit var todoRepository: TodoRepositoryImpl

    @BeforeEach
    fun before() {
        todoRepository.todoDateBase.init()
    }

    @Test
    fun save() {
        val todo = Todo().apply {
            this.title = "test"
            this.description = "do the test"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepository.save(todo)

        assertThat(result?.index).isEqualTo(1)
        assertThat(result?.createdAt).isNotNull
        assertThat(result?.title).isEqualTo("test")
        assertThat(result?.description).isEqualTo("do the test")

    }

    @Test
    fun saveAll() {
        val result = saveThreeSample()
        assertThat(result).isTrue

    }

    @Test
    fun findOne() {
        saveThreeSample()

        val findOne = todoRepository.findOne(2)
        assertThat(findOne).isNotNull
        assertThat(findOne?.title).isEqualTo("test2")

    }

    @Test
    fun update() {
        val todo = Todo().apply {
            this.title = "test"
            this.description = "do the test"
            this.schedule = LocalDateTime.now()
        }
        val insertTodo = todoRepository.save(todo)

        assertThat(todo.index).isEqualTo(1)
        val newTodo = Todo().apply {
            this.index = insertTodo?.index
            this.title = "update"
            this.description = "test update"
            this.schedule = LocalDateTime.now()
        }
        val result = todoRepository.save(newTodo)
        assertThat(result).isNotNull
        assertThat(result?.index).isEqualTo(insertTodo?.index)
        assertThat(result?.title).isEqualTo("update")
        assertThat(result?.description).isEqualTo("test update")
    }

    @Test
    fun delete() {
        saveThreeSample()
        val result = todoRepository.delete(2)
        assertThat(result).isTrue
        assertThrows<java.util.NoSuchElementException> { todoRepository.findOne(2) }
        assertThat(todoRepository.todoDateBase.todoList.size).isEqualTo(2)
    }

    @Test
    fun findAll() {
        saveThreeSample()
        val findAll = todoRepository.findAll()
        assertThat(findAll.size).isEqualTo(3)
    }

    private fun saveThreeSample(): Boolean {
        val todoList = mutableListOf(
            Todo().apply {
                this.title = "test"
                this.description = "do the test"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "test2"
                this.description = "do the test"
                this.schedule = LocalDateTime.now()
            },
            Todo().apply {
                this.title = "test3"
                this.description = "do the test"
                this.schedule = LocalDateTime.now()
            },
        )

        val result = todoRepository.saveAll(todoList)
        return result
    }

}
