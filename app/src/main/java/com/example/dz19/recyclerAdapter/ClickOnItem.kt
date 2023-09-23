package com.example.dz19.recyclerAdapter

import com.example.dz19.db.TaskEntity

interface ClickOnItem {
    fun click(taskEntity: TaskEntity)
}