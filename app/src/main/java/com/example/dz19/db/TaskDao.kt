package com.example.dz19.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert
    fun add(taskEntity: TaskEntity)

    @Delete
    fun delete(taskEntity: TaskEntity)

    @Query("SELECT * FROM task")
    fun getAll(): LiveData<List<TaskEntity>>

}