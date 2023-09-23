package com.example.dz19.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.dz19.App
import com.example.dz19.db.TaskEntity

class TaskViewModel : ViewModel() {
    private val repo = App.getApp().repo
    private val _liveDate = MutableLiveData<List<TaskEntity>>(repo.getAll().value)
    val liveData: LiveData<List<TaskEntity>> = _liveDate
    private val observer = Observer<List<TaskEntity>> {
        _liveDate.postValue(it)
    }

    init {
        repo.getAll().observeForever(observer)
    }

    fun add(taskEntity: TaskEntity) {
        repo.add(taskEntity = taskEntity)
    }

    fun delete(taskEntity: TaskEntity) {
        repo.delete(taskEntity = taskEntity)
    }

    override fun onCleared() {
        repo.getAll().removeObserver(observer)
        super.onCleared()
    }
}