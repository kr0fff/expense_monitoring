package com.example.expensemonitoring.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.expensemonitoring.R
import com.example.expensemonitoring.Room.Entities.Expenses
import com.example.expensemonitoring.Room.Repositories
import com.example.expensemonitoring.databinding.FragmentNewExpenseDetailsBinding
import com.example.expensemonitoring.fragments.viewModels.ExpenseDetailsViewModel
import com.example.expensemonitoring.fragments.viewModels.ViewModelFactory
import java.time.LocalDate
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentNewExpenseDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentNewExpenseDetails : Fragment() {

    private lateinit var view: FragmentNewExpenseDetailsBinding
    private val vm: ExpenseDetailsViewModel by viewModels { ViewModelFactory(Repositories) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = FragmentNewExpenseDetailsBinding.inflate(layoutInflater)

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        view.pickerDay.minValue = 1
        view.pickerDay.maxValue = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        view.pickerDay.value = day
        view.pickerDay.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

        view.pickerMonth.minValue = 1
        view.pickerMonth.maxValue = 12
        view.pickerMonth.value = month + 1
        view.pickerMonth.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

        view.pickerYear.minValue = year - 3
        view.pickerYear.maxValue = year
        view.pickerYear.value = year
        view.pickerYear.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS


        view.applyBtn.isEnabled = false

        val monthPickerListener = NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            val newDate = Calendar.getInstance()
            val data = newDate.set(view.pickerYear.value, newVal - 1, 1)
            view.pickerDay.maxValue = newDate.getActualMaximum(Calendar.DAY_OF_MONTH)
        }
        val yearPickerListener = NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            val newDate = Calendar.getInstance()
            newDate.set(newVal, view.pickerMonth.value - 1, 1)
            view.pickerDay.maxValue = newDate.getActualMaximum(Calendar.DAY_OF_MONTH)
        }
        view.pickerMonth.setOnValueChangedListener(monthPickerListener)
        view.pickerYear.setOnValueChangedListener(yearPickerListener)

        view.descriptionTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                view.applyBtn.isEnabled = view.descriptionTitle.text.toString().trim().isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        view.decimalValueSetter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val decimalFormat = Regex("^\\d+\\.\\d{2}$")

                if (!decimalFormat.matches(p0.toString()) || view.descriptionTitle.text.toString()
                        .trim()
                        .isEmpty()
                ) {
                    view.expenseErrorHint.text = resources.getString(R.string.expense_error_hint)
                    view.expenseErrorHint.isVisible = true
                    view.applyBtn.isEnabled = false
                } else {
                    view.expenseErrorHint.isVisible = false
                    view.applyBtn.isEnabled = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        view.applyBtn.setOnClickListener {
            val yyyy = view.pickerYear.value.toString()
            val mm = view.pickerMonth.value.toString()
            val dd = view.pickerDay.value.toString()
            val description = view.descriptionTitle.text.toString()
            val amount = view.decimalValueSetter.text.toString()
            val date = LocalDate.of(yyyy.toInt(), mm.toInt(), dd.toInt())
            val expense = Expenses.toExpense(
                date = date.toString(),
                description = description,
                amount = amount.toDouble()
            )
            vm.addExpenseRecord(expense)
            parentFragmentManager.popBackStack()
        }

        Log.d(
            "DEBUG_PICKER",
            "${view.pickerDay.value} ${view.pickerMonth.value} ${view.pickerYear.value}"
        )
        return view.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentNewExpenseDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentNewExpenseDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}