package com.example.expensemonitoring.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.expensemonitoring.R
import com.example.expensemonitoring.Room.Entities.Expenses
import com.example.expensemonitoring.Room.Repositories
import com.example.expensemonitoring.databinding.FragmentNewExpenseDetailsBinding
import com.example.expensemonitoring.fragments.viewModels.CategoriesViewModel
import com.example.expensemonitoring.fragments.viewModels.ExpenseDetailsViewModel
import com.example.expensemonitoring.fragments.viewModels.ViewModelFactory
import org.w3c.dom.Text
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
    private var param1: String? = null
    private var param2: String? = null
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

        with(view){
            pickerDay.minValue = 1
            pickerDay.maxValue = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            pickerDay.value = day

            pickerMonth.minValue = 1
            pickerMonth.maxValue = 12
            pickerMonth.value = month + 1

            pickerYear.minValue = year - 3
            pickerYear.maxValue = year
            pickerYear.value = year

            applyBtn.isEnabled = false

            descriptionTitle.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    applyBtn.isEnabled = descriptionTitle.text.toString().trim().isNotEmpty()
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })
            decimalValueSetter.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val decimalFormat = Regex("^\\d+\\.\\d{2}$")

                    if (!decimalFormat.matches(p0.toString()) || descriptionTitle.text.toString().trim().isEmpty()) {
                        expenseErrorHint.text = resources.getString(R.string.expense_error_hint)
                        expenseErrorHint.isVisible = true
                        applyBtn.isEnabled = false
                    } else {
                        expenseErrorHint.isVisible = false
                        applyBtn.isEnabled = true
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })

            applyBtn.setOnClickListener {
                val yyyy = pickerYear.value.toString()
                val mm = pickerMonth.value.toString()
                val dd = pickerDay.value.toString()
                val description = descriptionTitle.text.toString()
                val amount = decimalValueSetter.text.toString()
                val date = LocalDate.of(yyyy.toInt(), mm.toInt(), dd.toInt())
                val expense = Expenses.toExpense(
                    date = date.toString(),
                    description = description,
                    amount = amount.toDouble()
                )
                vm.addExpenseRecord(expense)
                parentFragmentManager.popBackStack()
            }
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