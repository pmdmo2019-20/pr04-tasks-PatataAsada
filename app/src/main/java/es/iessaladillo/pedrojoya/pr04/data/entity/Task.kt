package es.iessaladillo.pedrojoya.pr04.data.entity

import java.util.*

class Task(val concept: String) {

    val id: Int = count
    val createdAt: String = Date().toString()
    var completed: Boolean = false
    var completedAt: String = ""

    companion object {
        private var count: Int = 0
            get() {
                field++
                return count
            }
    }
}