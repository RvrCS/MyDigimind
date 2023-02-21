package torres.river.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import torres.river.mydigimind.R
import torres.river.mydigimind.databinding.FragmentDashboardBinding
import torres.river.mydigimind.ui.Task
import torres.river.mydigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val timeBtn: Button = root.findViewById(R.id.time_btn)

        timeBtn.setOnClickListener {

            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->

                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                timeBtn.text = SimpleDateFormat("HH:mm").format(cal.time)

            }

            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

        }

        val doneBtn = root.findViewById(R.id.done_button) as Button
        val nameTask = root.findViewById(R.id.name) as EditText
        val checkSunday = root.findViewById(R.id.sunday) as CheckBox
        val checkMonday = root.findViewById(R.id.monday) as CheckBox
        val checkTuesday = root.findViewById(R.id.tuesday) as CheckBox
        val checkWednesday = root.findViewById(R.id.wednesday) as CheckBox
        val checkThursday = root.findViewById(R.id.thursday) as CheckBox
        val checkFriday = root.findViewById(R.id.friday) as CheckBox
        val checkSaturday = root.findViewById(R.id.saturday) as CheckBox

        doneBtn.setOnClickListener {
            var title = nameTask.text.toString()
            var time = timeBtn.text.toString()
            var days = ArrayList<String>()

            if (checkSunday.isChecked)
                days.add("Sunday")
            if (checkMonday.isChecked)
                days.add("Monday")
            if (checkTuesday.isChecked)
                days.add("Tuesday")
            if (checkWednesday.isChecked)
                days.add("Wednesday")
            if (checkThursday.isChecked)
                days.add("Thursday")
            if (checkFriday.isChecked)
                days.add("Friday")
            if (checkSaturday.isChecked)
                days.add("Saturday")

            var task = Task(title, days, time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context, "New reminder added!", Toast.LENGTH_SHORT).show()

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}