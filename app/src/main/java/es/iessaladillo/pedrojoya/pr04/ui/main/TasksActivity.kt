package es.iessaladillo.pedrojoya.pr04.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.pr04.R
import es.iessaladillo.pedrojoya.pr04.base.observeEvent
import es.iessaladillo.pedrojoya.pr04.data.LocalRepository
import es.iessaladillo.pedrojoya.pr04.data.Repository
import es.iessaladillo.pedrojoya.pr04.data.entity.Task
import es.iessaladillo.pedrojoya.pr04.utils.invisibleUnless
import es.iessaladillo.pedrojoya.pr04.utils.setOnSwipeListener
import kotlinx.android.synthetic.main.tasks_activity.*


class TasksActivity : AppCompatActivity(), TasksActivityAdapter.OnItemClickListener {

    private var mnuFilter: MenuItem? = null
    private val viewModel: TasksActivityViewModel by viewModels {
        TasksActivityViewModelFactory(LocalRepository, application)
    }
    private val listAdapter: TasksActivityAdapter = TasksActivityAdapter().also {
        it.setOnItemClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_activity)
        setupObservers()
        setupViews()
    }

    private fun setupViews() {

        setupReciclerView()
        setupButtons()
    }

    private fun setupButtons() {
        imgAddTask.setOnClickListener{addTask()}
    }

    private fun addTask() {
        if(txtConcept.text.isNotEmpty()) viewModel.addTask(txtConcept.text.toString())
    }

    private fun setupReciclerView() {
        lstTasks.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@TasksActivity)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(
                DividerItemDecoration(
                    this@TasksActivity,
                    RecyclerView.VERTICAL
                )
            )
            setOnSwipeListener { viewHolder, _ ->
                viewModel.deleteTask(
                    listAdapter.getItem(
                        viewHolder.adapterPosition
                    )
                )
                showTasks(viewModel.tasks.value!!)
            }
            adapter = listAdapter
        }
    }

    private fun setupObservers() {
        viewModel.tasks.observe(this) {
            showTasks(viewModel.tasks.value!!)
        }
        //viewModel.onStartActivity.observeEvent(this) { doSomethingWithTheIntent(it) }
        viewModel.onShowMessage.observeEvent(this) { showSnackbar(it) }
        viewModel.onShowTaskDeleted.observeEvent(this) {showSnackbar(it)}
        viewModel.activityTitle.observe(this){ title = it }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(lstTasks, message, Snackbar.LENGTH_LONG).show()
    }
    private fun showSnackbar(task:Task){
        Snackbar.make(lstTasks,getString(R.string.tasks_task_deleted),Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.tasks_recreate)) { viewModel.insertTask(task) }
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        mnuFilter = menu.findItem(R.id.mnuFilter)
        menuInflater.inflate(R.menu.main_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnuShare -> viewModel.shareTasks()
            R.id.mnuDelete -> viewModel.deleteTasks()
            R.id.mnuComplete -> viewModel.markTasksAsCompleted()
            R.id.mnuPending -> viewModel.markTasksAsPending()
            R.id.mnuFilterAll -> viewModel.filterAll()
            R.id.mnuFilterPending -> viewModel.filterPending()
            R.id.mnuFilterCompleted -> viewModel.filterCompleted()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun checkMenuItem(@MenuRes menuItemId: Int) {
        lstTasks.post {
            val item = mnuFilter?.subMenu?.findItem(menuItemId)
            item?.let { menuItem ->
                menuItem.isChecked = true
            }
        }
    }

    private fun showTasks(tasks: List<Task>) {
        lstTasks.post {
            listAdapter.submitList(tasks)
            lblEmptyView.invisibleUnless(tasks.isEmpty())
        }
    }

    override fun onClick(adapterPosition: Int) {
        viewModel.updateTaskCompletedState(
            listAdapter.getItem(adapterPosition),
            !listAdapter.getItem(adapterPosition).completed
            )
        showTasks(viewModel.tasks.value!!)
    }

}

