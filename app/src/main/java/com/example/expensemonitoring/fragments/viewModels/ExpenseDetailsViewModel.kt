package com.example.expensemonitoring.fragments.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensemonitoring.Room.Entities.Expenses
import com.example.expensemonitoring.Room.Repositories
import kotlinx.coroutines.launch

class ExpenseDetailsViewModel(private val repository: Repositories): ViewModel() {
    private val categoriesRepo = repository.categoriesRepository

    fun addExpenseRecord(expense: Expenses){
        viewModelScope.launch {
            categoriesRepo.addExpenseRecord(expense)
        }
    }
}