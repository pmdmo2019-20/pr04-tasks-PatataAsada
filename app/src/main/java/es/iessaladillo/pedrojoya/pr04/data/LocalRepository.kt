package es.iessaladillo.pedrojoya.pr04.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.pr04.data.entity.Task

// TODO: Crea una clase llamada LocalRepository que implemente la interfaz Repository
//  usando una lista mutable para almacenar las tareas.
//  Los id de las tareas se irán generando secuencialmente a partir del valor 1 conforme
//  se van agregando tareas (add).

object LocalRepository: Repository{

    var taskList:List<Task> = listOf(
        Task("Hacer las cosas para hoy y no dejarlas para el ultimo dia")
    )

    override fun queryAllTasks(): List<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryCompletedTasks(): List<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryPendingTasks(): List<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTask(concept: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteTask(taskId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteTasks(taskIdList: List<Long>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun markTaskAsCompleted(taskId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun markTasksAsCompleted(taskIdList: List<Long>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun markTaskAsPending(taskId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun markTasksAsPending(taskIdList: List<Long>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
