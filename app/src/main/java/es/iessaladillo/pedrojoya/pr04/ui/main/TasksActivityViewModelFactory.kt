package es.iessaladillo.pedrojoya.pr04.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.pr04.data.Repository

class TasksActivityViewModelFactory(val repository: Repository, val application: Application) : ViewModelProvider.Factory {​

    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TasksActivityViewModel::class.java)) {

            return TasksActivityViewModel(repository,application) as T

        }

        throw IllegalArgumentException("Must provide MainActivityViewModel class")

    }​

}