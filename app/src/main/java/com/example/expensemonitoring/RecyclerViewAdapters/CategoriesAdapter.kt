package com.example.expensemonitoring.RecyclerViewAdapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemonitoring.databinding.ExpenseCategoryItemBinding

class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class CategoriesViewHolder(private val binding: ExpenseCategoryItemBinding): RecyclerView.ViewHolder(binding.root)
}