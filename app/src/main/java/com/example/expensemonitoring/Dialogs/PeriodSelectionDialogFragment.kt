package com.example.expensemonitoring.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
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

        return AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setTitle(R.string.dialog_period_selector_title)
            .setView(view.root)
            .setPositiveButton(R.string.action_confirm) { _, _ ->
             /*   val newVolume = dialogBinding.volumeSeekBar.progress
                parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_VOLUME_RESPONSE to newVolume))*/
            }
            .create()
    }
}