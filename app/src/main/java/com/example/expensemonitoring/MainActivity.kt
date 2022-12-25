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

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        Repositories.init(applicationContext)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    /*    PeriodSelectionDialogFragment.show(supportFragmentManager)
        PeriodSelectionDialogFragment.setupListener(supportFragmentManager, this){
            Log.d("MONTH_AND_YEAR", it.toString())
        }*/
    }
}