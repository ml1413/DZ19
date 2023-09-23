package com.example.dz19.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val label: String,
    val message: String,
)