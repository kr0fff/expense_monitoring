package com.example.expensemonitoring.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemonitoring.R
import com.example.expensemonitoring.Room.Repositories
import com.example.expensemonitoring.databinding.*
import com.example.expensemonitoring.fragments.VIewModels.CategoriesViewModel
import com.example.expensemonitoring.fragments.VIewModels.viewModelCreator


class FragmentCategoriesList : Fragment() {
    private val vm by viewModelCreator { CategoriesViewModel(Repositories.categoriesRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.expense_category_item, container, false)
    }

}