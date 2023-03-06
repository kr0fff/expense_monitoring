package com.example.expensemonitoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.example.expensemonitoring.Dialogs.PeriodSelectionDialogFragment
import com.example.expensemonitoring.Room.Repositories
import com.example.expensemonitoring.databinding.ActivityMainBinding
import com.example.expensemonitoring.fragments.FragmentCategoriesList
import com.example.expensemonitoring.fragments.FragmentNewExpenseDetails

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        Repositories.init(applicationContext)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, FragmentCategoriesList())
                .addToBackStack(null)
                .commit()
        }

            PeriodSelectionDialogFragment.show(supportFragmentManager)
            PeriodSelectionDialogFragment.setupListener(supportFragmentManager, this){
                Log.d("MONTH_AND_YEAR", it.toString())
            }
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}