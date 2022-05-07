package com.example.todo.database

data class TodoDataBase(
    var index: Int = 0,
    var todoList: MutableList<Todo> = mutableListOf(),
) {
    fun init(){
        this.todoList.clear()
        this.index = 0
        println("[DEBUG] todo database initialized")
    }
}
