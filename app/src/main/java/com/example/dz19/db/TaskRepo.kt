package com.example.dz19.db

import java.util.concurrent.Executors

class TaskRepo(
    private val database: TaskDatabase
) {
    private val executor = Executors.newSingleThreadExecutor()

    fun getAll() = database.taskDao().getAll()

    fun add(taskEntity: TaskEntity) {
        executor.execute { database.taskDao().add(taskEntity = taskEntity) }
    }

    fun delete(taskEntity: TaskEntity) {
        executor.execute { database.taskDao().delete(taskEntity = taskEntity) }
    }
}