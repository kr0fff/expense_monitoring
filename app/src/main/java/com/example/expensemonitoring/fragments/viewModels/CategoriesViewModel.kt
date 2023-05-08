package com.example.expensemonitoring.fragments.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensemonitoring.Room.Dao.CategoriesTuple
import com.example.expensemonitoring.Room.Repositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

class CategoriesViewModel(private val repository: Repositories) : ViewModel() {
    private val categoriesRepo = repository.categoriesRepository

    private val _categories = MutableLiveData<List<CategoriesTuple?>?>()
    val categories: LiveData<List<CategoriesTuple?>?> = _categories

    init {
        refreshCategories()
    }

    fun sortListByDateAndMonth() {
        refreshCategories()
        viewModelScope.launch {
            _categories.value = _categories.value
                ?.sortedWith(compareBy({ it?.categoryYear }, { it?.categoryMonth }))
                ?.reversed()
        }
    }

    fun refreshCategories() {
        viewModelScope.launch {
            val categoriesList = withContext(Dispatchers.IO) {
                categoriesRepo.getCategoriesRecords()
            }
            _categories.postValue(categoriesList)
            for (i in 1..10){
                println("From refresh method: $i")
            }
        }
        Log.d(
            "PROGRESS_DELETION",
            "refreshCategories() called, new liveData: ${categories.value}"
        )
    }

    /*suspend fun cancelableCategoriesDeletion(month: String, year: String) {
        categoriesRepo.deleteCategory(month,year)
    }*/

    fun deleteCategories(month: String, year: String) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                categoriesRepo.deleteCategory(month, year)
                for (i in 1..10){
                    println("From delete method: $i")
                }
            }
            refreshCategories() // обновление списка LiveData после удаления записи
        }

    }

}