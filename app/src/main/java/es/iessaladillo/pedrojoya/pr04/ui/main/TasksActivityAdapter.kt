package es.iessaladillo.pedrojoya.pr04.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr04.R
import es.iessaladillo.pedrojoya.pr04.data.entity.Task
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.tasks_activity_item.*

// TODO:
//  La barra de cada elemento tiene un color distinto dependiendo de si la tarea está
//  completada o no.
//  Debajo del concepto se muestra cuando fue creada la tarea, si la tarea está pendiente,
//  o cuando fue completada si la tarea ya ha sido completada.
//  Si la tarea está completada, el checkBox estará chequeado y el concepto estará tachado.

class TasksActivityAdapter() :
    RecyclerView.Adapter<TasksActivityAdapter.ViewHolder>() {

    private var onClickListener: OnItemClickListener? = null
    private var data: List<Task> = emptyList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(R.layout.tasks_activity_item, parent, false)

        return ViewHolder(itemView, onClickListener)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        onClickListener = listener
    }

    fun submitList(taskList: List<Task>) {
        data = taskList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long = data[position].id

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun getItem(adapterPosition: Int): Task = data[adapterPosition]

    inner class ViewHolder(override val containerView: View, onClickListener: OnItemClickListener?) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        init {
            containerView.setOnClickListener{ onClickListener?.onClick(adapterPosition) }
        }

        fun bind(task: Task) {
            lblConcept.text = task.concept
            chkCompleted.isChecked = task.completed
            if (task.completed) lblCompleted.text =
                String.format(containerView.context.getString(R.string.tasks_item_completedAt),task.completedAt)
            else lblCompleted.text =
                String.format(containerView.context.getString(R.string.tasks_item_createdAt),task.createdAt)
        }
    }

    interface OnItemClickListener {
        fun onClick(adapterPosition: Int)
    }
}


