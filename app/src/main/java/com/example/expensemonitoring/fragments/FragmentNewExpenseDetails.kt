package com.example.expensemonitoring.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.expensemonitoring.R
import com.example.expensemonitoring.Room.Repositories
import com.example.expensemonitoring.databinding.FragmentNewExpenseDetailsBinding
import com.example.expensemonitoring.fragments.viewModels.CategoriesViewModel
import com.example.expensemonitoring.fragments.viewModels.ViewModelFactory
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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var view: FragmentNewExpenseDetailsBinding
    // private val vm: CategoriesViewModel by viewModels { ViewModelFactory(Repositories) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = FragmentNewExpenseDetailsBinding.inflate(layoutInflater)

      /*  arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)

        view.pickerDay.minValue = 1
        view.pickerDay.maxValue = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        view.pickerDay.value = day

        view.pickerMonth.minValue = 1
        view.pickerMonth.maxValue = 12
        view.pickerMonth.value = month

        view.pickerYear.minValue = year - 3
        view.pickerYear.maxValue = year
        view.pickerYear.value = year
        Log.d("DEBUG_PICKER", "${view.pickerDay.value.toString()} ${view.pickerMonth.value.toString()} ${view.pickerYear.value.toString()}")
        return inflater.inflate(R.layout.fragment_new_expense_details, container, false)
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