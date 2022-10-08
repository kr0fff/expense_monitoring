package com.example.expensemonitoring.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.example.expensemonitoring.R
import com.example.expensemonitoring.databinding.FragmentPeriodSelectionBinding
import java.util.*

class PeriodSelectionDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = FragmentPeriodSelectionBinding.inflate(layoutInflater)
        val calendar = Calendar.getInstance()
        val monthPicker = view.pickerMonth
        val yearPicker = view.pickerYear

        monthPicker.minValue = 0
        monthPicker.maxValue = 11
        monthPicker.value = calendar.get(Calendar.MONTH)

        val year = calendar.get(Calendar.YEAR)
        yearPicker.minValue = 1970
        yearPicker.maxValue = year
        yearPicker.value = year


        return AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setTitle(R.string.dialog_period_selector_title)
            .setView(view.root)
            .setPositiveButton(R.string.action_confirm) { _, _ ->
                parentFragmentManager.setFragmentResult(REQUEST_KEY,
                    bundleOf(MONTH_VALUE_RESPONSE to monthPicker.value,
                        YEAR_VALUE_RESPONSE to yearPicker.value))
            }
            .create()
    }

    companion object {
        @JvmStatic private val TAG = PeriodSelectionDialogFragment::class.java.simpleName
        @JvmStatic private val MONTH_VALUE_RESPONSE = "MONTH_VALUE_RESPONSE"
        @JvmStatic private val YEAR_VALUE_RESPONSE = "YEAR_VALUE_RESPONSE"
        // @JvmStatic private val ARG_VOLUME = "ARG_VOLUME"

        @JvmStatic val REQUEST_KEY = "$TAG:defaultRequestKey"

        fun show(manager: FragmentManager) {
            val dialogFragment = PeriodSelectionDialogFragment()
            dialogFragment.show(manager, TAG)
        }

        fun setupListener(manager: FragmentManager, lifecycleOwner: LifecycleOwner, listener: (Bundle?) -> Unit) {
            manager.setFragmentResultListener(REQUEST_KEY, lifecycleOwner, FragmentResultListener { _, result ->

                listener.invoke(result.getBundle(REQUEST_KEY))
            })
        }
    }
}