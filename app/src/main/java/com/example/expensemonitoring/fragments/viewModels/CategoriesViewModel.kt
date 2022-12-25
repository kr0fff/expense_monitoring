package com.example.expensemonitoring.fragments.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensemonitoring.Room.Dao.CategoriesTuple
import com.example.expensemonitoring.Room.Repositories
import kotlinx.coroutines.launch

class CategoriesViewModel(private val repository: Repositories): ViewModel() {
    private val categoriesRepo = repository.categoriesRepository

    private val _categories = MutableLiveData<List<CategoriesTuple?>>()
    val categories: LiveData<List<CategoriesTuple?>> = _categories

    fun getCategoriesList() {
        viewModelScope.launch {
            _categories.value = categoriesRepo.getCategoriesRecords()
        }
    }
}