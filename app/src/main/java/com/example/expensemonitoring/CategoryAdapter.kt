package com.example.expensemonitoring

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemonitoring.Room.Dao.CategoriesTuple
import com.example.expensemonitoring.databinding.ExpenseCategoryItemBinding
import java.time.Month

class CategoryAdapter(
    private val categories: List<CategoriesTuple?>,
    private val resources: Resources
): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExpenseCategoryItemBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        with(holder.binding) {
            if (category != null) {
                val month = Month.of(category.categoryMonth.toInt())
                val totalSpent = kotlin.math.round(category.categorySum * 100) / 100
                categoryLabel.text = resources.getString(R.string.category_description, month, category.categoryYear)
                categoryDescriptionTotal.text = resources.getString(R.string.category_total, totalSpent.toString())
            }
        }
    }

    override fun getItemCount(): Int = categories.size

    class CategoryViewHolder(
       val binding: ExpenseCategoryItemBinding
    ): RecyclerView.ViewHolder(binding.root)
}