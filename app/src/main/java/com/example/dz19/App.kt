package com.example.dz19

import android.app.Application
import androidx.room.Room
import com.example.dz19.db.TaskDatabase
import com.example.dz19.db.TaskRepo

class App : Application() {
    lateinit var repo: TaskRepo
    override fun onCreate() {
        super.onCreate()
        instance = this

        val db = Room.databaseBuilder(
            context = this,
            klass = TaskDatabase::class.java,
            name = "task_database"
        ).build()
        repo = TaskRepo(db)
    }

    companion object {
        private lateinit var instance: App
        fun getApp() = instance
    }
}