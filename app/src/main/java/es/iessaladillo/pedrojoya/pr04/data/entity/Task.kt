package es.iessaladillo.pedrojoya.pr04.data.entity

import java.util.*

class Task(val concept: String) {

    val id: Long = count
    val createdAt: String = Date().toString()
    var completed: Boolean = false
    var completedAt: String = ""

    companion object {
        private var count: Long = 0
            get() {
                field++
                return count
            }
    }
}