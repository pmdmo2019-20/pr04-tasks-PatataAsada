package es.iessaladillo.pedrojoya.pr04.data.entity

import java.util.*

class Task(val concept: String) {

    val id: Long = getCount()
    val createdAt: String = Date().toString()
    var completed: Boolean = false
    var completedAt: String = ""

    companion object {
        private var count: Long = 0
        fun getCount():Long{
            count++
            return count
        }
    }
}