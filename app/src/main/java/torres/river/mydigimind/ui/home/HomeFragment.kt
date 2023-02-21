package torres.river.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import torres.river.mydigimind.R
import torres.river.mydigimind.databinding.FragmentHomeBinding
import torres.river.mydigimind.ui.Task

class HomeFragment : Fragment() {


    private var adapter: AdaptadorTareas? = null

    private var _binding: FragmentHomeBinding? = null

    companion object{
        var tasks = ArrayList<Task>()
        var first = true
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (first){
            fillTask()
            first = false
        }


        adapter = AdaptadorTareas(root.context, tasks)

        val gridTasks: GridView = root.findViewById(R.id.reminders)

        gridTasks.adapter = adapter

        return root
    }

    fun fillTask(){

        tasks.add(Task("Practice 1", arrayListOf("Monday", "Sunday"), "17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Wednesday", "Sunday"), "12:30"))
        tasks.add(Task("Practice 3", arrayListOf("Thursday", "Friday"), "19:00"))
        tasks.add(Task("Practice 4", arrayListOf("Monday"), "18:00"))
        tasks.add(Task("Practice 5", arrayListOf("Monday", "Wednesday", "Friday"), "14:00"))
        tasks.add(Task("Practice 6", arrayListOf("Sunday"), "8:30"))
        tasks.add(Task("Practice 7", arrayListOf("Tuesday", "Sunday"), "10:30"))

    }

    private class AdaptadorTareas: BaseAdapter {

        var tasks = ArrayList<Task>()
        var context: Context? = null

        constructor(context: Context, tasks: ArrayList<Task>){

            this.context = context
            this.tasks = tasks

        }

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(p0: Int): Any {
            return tasks[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var task = tasks[p0]
            var inflater = LayoutInflater.from(context)
            var view = inflater.inflate(R.layout.task_view, null)

            var title: TextView = view.findViewById(R.id.title_reminder)
            var days: TextView = view.findViewById(R.id.days_reminder)
            var time: TextView = view.findViewById(R.id.time_reminder)

            title.setText(task.title)
            days.setText(task.days.toString())
            time.setText(task.time)

            return view
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}