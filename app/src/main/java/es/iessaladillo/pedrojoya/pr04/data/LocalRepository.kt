package es.iessaladillo.pedrojoya.pr04.data

import es.iessaladillo.pedrojoya.pr04.data.entity.Task
import java.util.*

object LocalRepository : Repository {

    private val taskList: MutableList<Task> = mutableListOf()

    override fun queryAllTasks(): List<Task> {
        return taskList.sortedWith(compareBy { it.id })
    }

    override fun queryCompletedTasks(): List<Task> {
        taskList.sortedWith(compareBy { it.id })
        return taskList.filter { it.completed }
    }

    override fun queryPendingTasks(): List<Task> {
        taskList.sortedWith(compareBy { it.id })
        return taskList.filter { !it.completed }
    }

    override fun addTask(concept: String) {
        var newTask = Task(concept)
        insertTask(newTask)
    }

    override fun insertTask(task: Task) {
        taskList.plus(task)
    }

    override fun deleteTask(taskId: Long) {
        taskList.remove(taskList.find { it.id == taskId })
    }

    override fun deleteTasks(taskIdList: List<Long>) {
        taskIdList.forEach { deleteTask(it) }
    }

    override fun markTaskAsCompleted(taskId: Long) {
        taskList[taskList.indexOf(taskList.find { it.id == taskId })].completed = true
        taskList[taskList.indexOf(taskList.find { it.id == taskId })].completedAt =
            Date().toString()
    }

    override fun markTasksAsCompleted(taskIdList: List<Long>) {
        taskIdList.forEach { markTaskAsCompleted(it) }
    }

    override fun markTaskAsPending(taskId: Long) {
        taskList[taskList.indexOf(taskList.find { it.id == taskId })].completed = false
        taskList[taskList.indexOf(taskList.find { it.id == taskId })].completedAt = ""
    }

    override fun markTasksAsPending(taskIdList: List<Long>) {
        taskIdList.forEach { markTaskAsPending(it) }
    }

}
