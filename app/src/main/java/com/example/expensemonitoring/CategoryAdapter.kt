package com.example.expensemonitoring

import android.content.res.Resources
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemonitoring.Room.Dao.CategoriesTuple
import com.example.expensemonitoring.databinding.ActivityMainBinding.inflate
import com.example.expensemonitoring.databinding.ExpenseCategoryItemBinding

class CategoryAdapter(
    private val categories: List<CategoriesTuple?>
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

                categoryLabel.text = String.format(R.string.category_label.toString(), category.categoryMonth, category.categoryYear)
              /*  categoryLabel.text = getString(
                    R.string.category_label,
                    category.categoryMonth,
                    category.categoryYear
                )*/
                categoryDescriptionTotal.text = category.categorySum.toString()
            }
        }
    }

    override fun getItemCount(): Int = categories.size

    class CategoryViewHolder(
       val binding: ExpenseCategoryItemBinding
    ): RecyclerView.ViewHolder(binding.root)
}