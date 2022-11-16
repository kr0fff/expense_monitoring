package com.example.expensemonitoring

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemonitoring.databinding.ActivityMainBinding.inflate
import com.example.expensemonitoring.databinding.ExpenseCategoryItemBinding

class CategoryAdapter(): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExpenseCategoryItemBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    class CategoryViewHolder(
        binding: ExpenseCategoryItemBinding
    ): RecyclerView.ViewHolder(binding.root)
}